package com.ems.estatemanagementsystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ems.estatemanagementsystem.dto.DistrictDTO;
import com.ems.estatemanagementsystem.dto.ExternalAgencyDTO;
import com.ems.estatemanagementsystem.dto.InquiryDTO;
import com.ems.estatemanagementsystem.dto.StateDTO;
import com.ems.estatemanagementsystem.entity.ExternalAgency;
import com.ems.estatemanagementsystem.entity.Inquiry;
import com.ems.estatemanagementsystem.entity.User;
import com.ems.estatemanagementsystem.service.DistrictService;
import com.ems.estatemanagementsystem.service.ExternalAgencyService;
import com.ems.estatemanagementsystem.service.InquiryService;
import com.ems.estatemanagementsystem.service.StateService;
import com.ems.estatemanagementsystem.service.UserService;
import com.ems.estatemanagementsystem.service.emailservice.EmailService;

@Controller
public class InquiryController {
    
    private final InquiryService inquiryService;
    private final UserService userService;
    private final ExternalAgencyService externalAgencyService;
    private final StateService stateService;
    private final DistrictService districtService;
    private final EmailService emailService;

    @Value("${stripe.public.key}")
    private String stripePublicKey;

    public InquiryController ( InquiryService inquiryService, UserService userService, ExternalAgencyService externalAgencyService, StateService stateService, DistrictService districtService, EmailService emailService) {
        this.inquiryService = inquiryService;
        this.userService = userService;
        this.externalAgencyService = externalAgencyService;
        this.stateService = stateService;
        this.districtService = districtService;
        this.emailService = emailService;
    }

    @GetMapping("/inquiry/create")
    public String newInquiry(Model model) {
        User user = userService.getCurrentUser();
        Inquiry inquiry = new Inquiry();
        inquiry.setUser(user);
        List<ExternalAgencyDTO> externalAgencyList = externalAgencyService.getExternalAgencyList();
        List<StateDTO> stateList = stateService.getStateList();
        List<DistrictDTO> districtList = districtService.getDistrictList();
        List<ExternalAgencyDTO> agencyList = externalAgencyService.getExternalAgencyList();
        model.addAttribute("user", user);
        model.addAttribute("externalAgencyList", externalAgencyList);
        model.addAttribute("inquiry", inquiry);
        model.addAttribute("stateList", stateList);
        model.addAttribute("districtList", districtList);
        model.addAttribute("agencyList", agencyList);

        return "createInquiry";
    }

    @PostMapping("/inquiry/submit")
    public String submitInquiry(@ModelAttribute Inquiry inquiry,
                                @RequestParam("agencyIds") List<Long> agencyIds,
                                @RequestParam("icFile") MultipartFile icFile,
                                @RequestParam("deathCert") MultipartFile deathCert,
                                Model model) {

        try {
            // Create the inquiry
            inquiryService.createInquiry(inquiry, agencyIds, icFile, deathCert);

            // Send email notification
            String emailBody = "An inquiry has been submitted successfully.\n"
                            + "Inquiry Details:\n"
                            + "This is test email \n";

            emailService.sendMessage(userService.getCurrentUser().getEmail(), "New Inquiry", emailBody);
            
        } catch (IOException e) {
            model.addAttribute("error", "File upload failed: " + e.getMessage());
            return "redirect:/users";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create inquiry: " + e.getMessage());
            return "redirect:/users";
        }

        model.addAttribute("success", true);
        return "redirect:/inquiry/list/";
    }

    @GetMapping("/admin/inquiry/list/")
    public String listUserWasiat(Model model) {
        List<InquiryDTO> inquiryList = inquiryService.getInquiryList();
        model.addAttribute("inquiryList", inquiryList);
        return "inquiryUserViewList";
    }

    @GetMapping("/inquiry/list/")
    public String listWasiat(Model model) {
        List<InquiryDTO> inquiryList = inquiryService.getInquiryList();
        model.addAttribute("inquiryList", inquiryList);
        return "inquiryViewList";
    }

    @GetMapping("/admin/inquiry/view/{inquiryId}")
    public String viewInquiryUser(@PathVariable Long inquiryId, Model model) {
        InquiryDTO inquiry = inquiryService.findInquiryById(inquiryId);
        model.addAttribute("inquiry", inquiry);
        return "viewInquiry";
    }

    @GetMapping("/inquiry/view/{inquiryId}")
    public String viewInquiryAdmin(@PathVariable Long inquiryId, Model model) {
        InquiryDTO inquiry = inquiryService.findInquiryById(inquiryId);
        model.addAttribute("inquiry", inquiry);
        return "viewInquiry";
    }

    @GetMapping("/admin/inquiry/confirm/{inquiryId}")
    public String confirmInquiryUser(@PathVariable Long inquiryId, Model model) {
        InquiryDTO inquiry = inquiryService.findInquiryById(inquiryId);
        model.addAttribute("inquiry", inquiry);
        return "confirmInquiry";
    }

    @PostMapping("/admin/inquiry/confirm/submit")
    public String submitConfirmInquiryUser(@ModelAttribute Inquiry inquiry, @RequestParam Map<String, String> requestParams, Model model) {
        List<ExternalAgency> agencies = inquiryService.findInquiryById(inquiry.getId()).getInquiryAgency();
        List<String> inquiryAgencyStatus = new ArrayList<>();
        boolean hasAvailable = false;
        boolean hasNullStatus = false;

        float serviceFee = 0;

        for (int i = 0; i < agencies.size(); i++) {
            String status = requestParams.get("status-" + i);
            if (status == null) {
                inquiryAgencyStatus.add("Waiting for Feedback"); // Default value if none selected
                hasNullStatus = true;
            } else {
                inquiryAgencyStatus.add(status);
                if ("Estate Available".equals(status)) {
                    hasAvailable = true;
                    serviceFee = serviceFee + agencies.get(i).getServiceFee();
                }
            }
        }

        // Set the overall inquiry status based on the presence of null statuses first, then "Available" status
        if (hasNullStatus) {
            inquiry.setStatus("Inquiry Sent");
        } else if (hasAvailable && !hasNullStatus) {
            inquiry.setStatus("Estate Available, Pending Payment");
            inquiry.setTotalPayment(serviceFee);
        } else {
            inquiry.setStatus("Inquiry Ended");
        }

        inquiry.setInquiryAgencyStatus(inquiryAgencyStatus);
        // Save the inquiry entity with the updated status
        inquiryService.updateInquiry(inquiry);

        List<InquiryDTO> inquiryList = inquiryService.getInquiryList();
        model.addAttribute("inquiryList", inquiryList);

        return "redirect:/admin/inquiry/list/";
    }

    @GetMapping("/inquiry/checkout/{inquiryId}")
    public String inquiryPayment(@PathVariable Long inquiryId, Model model) {
        InquiryDTO inquiry = inquiryService.findInquiryById(inquiryId);
        model.addAttribute("inquiry", inquiry);
        model.addAttribute("stripePublicKey", stripePublicKey);
        return "checkout";
    }

    @GetMapping("/inquiry/checkout/done/{inquiryId}")
    public String inqiuryPaymentCheckout(@PathVariable Long inquiryId, Model model) {
        Inquiry inquiry = inquiryService.findInquiryNotDTOById(inquiryId);

        inquiry.setStatus("Payment Completed, Inquiry Ended");
        inquiryService.updateInquiry(inquiry);

        List<InquiryDTO> inquiryList = inquiryService.getInquiryList();
        model.addAttribute("inquiryList", inquiryList);

        return "redirect:/inquiry/list/";
    }
    
}
