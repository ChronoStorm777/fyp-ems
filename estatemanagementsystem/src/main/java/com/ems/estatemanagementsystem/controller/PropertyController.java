package com.ems.estatemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ems.estatemanagementsystem.entity.Land;
import com.ems.estatemanagementsystem.entity.Property;
import com.ems.estatemanagementsystem.entity.User;
import com.ems.estatemanagementsystem.entity.property.Bond;
import com.ems.estatemanagementsystem.entity.property.Cash;
import com.ems.estatemanagementsystem.entity.property.Debenture;
import com.ems.estatemanagementsystem.entity.property.Insurance;
import com.ems.estatemanagementsystem.entity.property.Share;
import com.ems.estatemanagementsystem.entity.property.UnitTrust;
import com.ems.estatemanagementsystem.entity.property.Vehicle;
import com.ems.estatemanagementsystem.entity.property.land.Caveat;
import com.ems.estatemanagementsystem.entity.property.land.Charge;
import com.ems.estatemanagementsystem.entity.property.land.Easement;
import com.ems.estatemanagementsystem.entity.property.land.Lease;
import com.ems.estatemanagementsystem.entity.property.land.Maintenance;
import com.ems.estatemanagementsystem.entity.property.land.Mortgage;
import com.ems.estatemanagementsystem.entity.property.land.QuitRent;
import com.ems.estatemanagementsystem.entity.property.land.RightOfWay;
import com.ems.estatemanagementsystem.entity.property.land.Tenancy;
import com.ems.estatemanagementsystem.entity.property.land.Transfer;
import com.ems.estatemanagementsystem.entity.property.land.UtilitiesBill;
import com.ems.estatemanagementsystem.entity.property.land.Waqf;
import com.ems.estatemanagementsystem.service.ContractService;
import com.ems.estatemanagementsystem.service.LandService;
import com.ems.estatemanagementsystem.service.PropertyService;
import com.ems.estatemanagementsystem.service.UserService;
import com.ems.estatemanagementsystem.service.propertyservice.BondService;
import com.ems.estatemanagementsystem.service.propertyservice.CashService;
import com.ems.estatemanagementsystem.service.propertyservice.DebentureService;
import com.ems.estatemanagementsystem.service.propertyservice.InsuranceService;
import com.ems.estatemanagementsystem.service.propertyservice.ShareService;
import com.ems.estatemanagementsystem.service.propertyservice.UnitTrustService;
import com.ems.estatemanagementsystem.service.propertyservice.VehicleService;
import com.ems.estatemanagementsystem.service.propertyservice.landservice.CaveatService;
import com.ems.estatemanagementsystem.service.propertyservice.landservice.ChargeService;
import com.ems.estatemanagementsystem.service.propertyservice.landservice.EasementService;
import com.ems.estatemanagementsystem.service.propertyservice.landservice.LeaseService;
import com.ems.estatemanagementsystem.service.propertyservice.landservice.MaintenanceService;
import com.ems.estatemanagementsystem.service.propertyservice.landservice.MortgageService;
import com.ems.estatemanagementsystem.service.propertyservice.landservice.QuitRentService;
import com.ems.estatemanagementsystem.service.propertyservice.landservice.RightOfWayService;
import com.ems.estatemanagementsystem.service.propertyservice.landservice.TenancyService;
import com.ems.estatemanagementsystem.service.propertyservice.landservice.TransferService;
import com.ems.estatemanagementsystem.service.propertyservice.landservice.UtilitiesBillService;
import com.ems.estatemanagementsystem.service.propertyservice.landservice.WaqfService;

@Controller
public class PropertyController {
    
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private UserService userService;
    @Autowired
    private CaveatService caveatService;
    @Autowired
    private ChargeService chargeService;
    @Autowired
    private EasementService easementService;
    @Autowired
    private LeaseService leaseService;
    @Autowired
    private MaintenanceService maintenanceService;
    @Autowired
    private MortgageService mortgageService;
    @Autowired
    private QuitRentService quitRentService;
    @Autowired
    private RightOfWayService rightOfWayService;
    @Autowired
    private TenancyService tenancyService;
    @Autowired
    private TransferService transferService;
    @Autowired
    private UtilitiesBillService utilitiesBillService;
    @Autowired
    private WaqfService waqfService;
    @Autowired
    private BondService bondService;
    @Autowired
    private CashService cashService;
    @Autowired
    private DebentureService debentureService;
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private ShareService shareService;
    @Autowired
    private UnitTrustService unitTrustService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private LandService landService;
    @Autowired
    private ContractService contractService;
    
    public PropertyController(PropertyService propertyService, UserService userService) {
        this.propertyService = propertyService;
        this.userService = userService;
    }

    private User getLoggedInUser() {
        return userService.getCurrentUser();
    }

    //display list of properties
    @GetMapping("/propertyList")
    public String propertyList(Model model){
        User loggedInUser = getLoggedInUser();
        List<Property> propertyList = propertyService.getPropertiesByUser(loggedInUser);

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("propertyList", propertyList);
        return "propertyList";
    }

    //BOND
    @GetMapping("/formbond")
    public String formbond(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property bond = new Bond();
        bond.setUser(loggedInUser);

        model.addAttribute("Bond", bond);
        return "formBond";
    }

    @PostMapping("/saveBond")
    public String saveBond(@ModelAttribute("Bond") Bond bond, Model model){

        bondService.saveBond(bond);
        model.addAttribute("successMessage", "Bond saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/formbondupdate/{id}")
    public String formbondupdate(@PathVariable Long id, Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Bond existingBond = bondService.getBondById(id);

        model.addAttribute("Bond", existingBond);
        return "formBondUpdate";
    }

    @PostMapping("/saveBondUpdate")
    public String saveBondUpdate(@ModelAttribute("Bond") Bond bond, Model model){
        bondService.updateBond(bond);
        
        model.addAttribute("successMessage", "Bond saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteBond/{id}")
	public String deleteBond(@PathVariable Long id) {
		bondService.deleteBondById(id);
		return "redirect:/propertyList";
	}

    //CAVEAT
    @GetMapping("/formcaveat")
    public String formcaveat(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property caveat = new Caveat();
        caveat.setUser(loggedInUser);

        model.addAttribute("Caveat", caveat);
        return "formCaveat";
    }

    @PostMapping("/saveCaveat")
    public String saveCaveat(@ModelAttribute("Caveat") Caveat caveat, Model model){
        Land existingLand = landService.getLandByTitleId(caveat.getTitleId());

        if(existingLand != null){
            model.addAttribute("msg", "Land with Title Id is already exist.");

            return "formcaveat";
        }

        caveatService.saveCaveat(caveat);

        return "redirect:/propertyList";
    }

    @GetMapping("/formcaveatupdate/{id}")
    public String formcaveatupdate(@PathVariable Long id, Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Caveat existingCaveat = caveatService.getCaveatById(id);

        model.addAttribute("Caveat", existingCaveat);
        return "formCaveatUpdate";
    }

    @PostMapping("/saveCaveatUpdate")
    public String saveCaveatUpdate(@ModelAttribute("Caveat") Caveat caveat, Model model){
        caveatService.updateCaveat(caveat);
        
        model.addAttribute("successMessage", "Caveat saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteCaveat/{id}")
	public String deleteCaveat(@PathVariable Long id) {
		caveatService.deleteCaveatById(id);
		return "redirect:/propertyList";
	}

    //CHARGE
    @GetMapping("/formcharge")
    public String formcharge(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property charge = new Charge();
        charge.setUser(loggedInUser);

        model.addAttribute("Charge", charge);
        return "formCharge";
    }

    @PostMapping("/saveCharge")
    public String saveCharge(@ModelAttribute("Charge") Charge charge, Model model){
        Land existingLand = landService.getLandByTitleId(charge.getTitleId());

        if(existingLand != null){
            model.addAttribute("msg", "Land with Title Id is already exist.");

            return "formcharge";
        }

        chargeService.saveCharge(charge);

        return "redirect:/propertyList";
    }

    @GetMapping("/formchargeupdate/{id}")
    public String formchargeupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Charge existingCharge = chargeService.getChargeById(id);

        model.addAttribute("Charge", existingCharge);
        return "formChargeUpdate";
    }

    @PostMapping("/saveChargeUpdate")
    public String saveChargeUpdate(@ModelAttribute("Charge") Charge charge, Model model){
        chargeService.updateCharge(charge);
        
        model.addAttribute("successMessage", "Charge saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteCharge/{id}")
	public String deleteCharge(@PathVariable Long id) {
		chargeService.deleteChargeById(id);
		return "redirect:/propertyList";
	}

    //EASEMENT
    @GetMapping("/formeasement")
    public String formeasement(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property easement = new Easement();
        easement.setUser(loggedInUser);

        model.addAttribute("Easement", easement);
        return "formEasement";
    }

    @PostMapping("/saveEasement")
    public String saveEasement(@ModelAttribute("Easement") Easement easement, Model model){
        Land existingLand = landService.getLandByTitleId(easement.getTitleId());

        if(existingLand != null){
            model.addAttribute("msg", "Land with Title Id is already exist.");

            return "formeasement";
        }

        easementService.saveEasement(easement);

        return "redirect:/propertyList";
    }

    @GetMapping("/formeasementupdate/{id}")
    public String formeasementupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Easement existingeEasement = easementService.getEasementById(id);

        model.addAttribute("Easement", existingeEasement);
        return "formEasementUpdate";
    }

    @PostMapping("/saveEasementUpdate")
    public String saveEasementUpdate(@ModelAttribute("Easement") Easement easement, Model model){
        easementService.updateEasement(easement);
        
        model.addAttribute("successMessage", "Easement saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteEasement/{id}")
	public String deleteEasement(@PathVariable Long id) {
		easementService.deleteEasementById(id);
		return "redirect:/propertyList";
	}

    //LEASE
    @GetMapping("/formlease")
    public String formlease(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property lease = new Lease();
        lease.setUser(loggedInUser);

        model.addAttribute("Lease", lease);
        return "formLease";
    }

    @PostMapping("/saveLease")
    public String saveLease(@ModelAttribute("Lease") Lease lease, Model model){
        Land existingLand = landService.getLandByTitleId(lease.getTitleId());

        if(existingLand != null){
            model.addAttribute("msg", "Land with Title Id is already exist.");

            return "formlease";
        }

        leaseService.saveLease(lease);

        return "redirect:/propertyList";
    }

    @GetMapping("/formleaseupdate/{id}")
    public String formleaseupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Lease existingLease = leaseService.getLeaseById(id);

        model.addAttribute("Lease", existingLease);
        return "formLeaseUpdate";
    }

    @PostMapping("/saveLeaseUpdate")
    public String saveLeaseUpdate(@ModelAttribute("Lease") Lease lease, Model model){
        leaseService.updateLease(lease);
        
        model.addAttribute("successMessage", "Lease saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteLease/{id}")
	public String deleteLease(@PathVariable Long id) {
		leaseService.deleteLeaseById(id);
		return "redirect:/propertyList";
	}

    //MAINTENANCE
    @GetMapping("/formmaintenance")
    public String formmaintenance(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property maintenance = new Maintenance();
        maintenance.setUser(loggedInUser);

        model.addAttribute("Maintenance", maintenance);
        return "formmaintenance";
    }

    @PostMapping("/saveMaintenance")
    public String saveMaintenance(@ModelAttribute("Maintenance") Maintenance maintenance, Model model){
        Land existingLand = landService.getLandByTitleId(maintenance.getTitleId());

        if(existingLand != null){
            model.addAttribute("msg", "Land with Title Id is already exist.");

            return "formmaintenance";
        }

        maintenanceService.saveMaintenance(maintenance);

        return "redirect:/propertyList";
    }

    @GetMapping("/formmaintenanceupdate/{id}")
    public String formmaintenanceupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Maintenance existingMaintenance = maintenanceService.getMaintenanceById(id);

        model.addAttribute("Maintenance", existingMaintenance);
        return "formMaintenanceUpdate";
    }

    @PostMapping("/saveMaintenanceUpdate")
    public String saveMaintenanceUpdate(@ModelAttribute("Maintenance") Maintenance maintenance, Model model){
        maintenanceService.updateMaintenance(maintenance);
        
        model.addAttribute("successMessage", "Maintenance saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteMaintenance/{id}")
	public String deleteMaintenance(@PathVariable Long id) {
		maintenanceService.deleteMaintenanceById(id);
		return "redirect:/propertyList";
	}

    //MORTGAGE
    @GetMapping("/formmortgage")
    public String formmortgage(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property mortgage = new Mortgage();
        mortgage.setUser(loggedInUser);

        model.addAttribute("Mortgage", mortgage);
        return "formMortgage";
    }

    @PostMapping("/saveMortgage")
    public String saveMortgage(@ModelAttribute("Mortgage") Mortgage mortgage, Model model){
        Land existingLand = landService.getLandByTitleId(mortgage.getTitleId());

        if(existingLand != null){
            model.addAttribute("msg", "Land with Title Id is already exist.");

            return "formmortgage";
        }

        mortgageService.saveMortgage(mortgage);

        return "redirect:/propertyList";
    }

    @GetMapping("/formmortgageupdate/{id}")
    public String formmortgageupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Mortgage existingMortgage = mortgageService.getMortgageById(id);

        model.addAttribute("Mortgage", existingMortgage);
        return "formMortgageUpdate";
    }

    @PostMapping("/saveMortgageUpdate")
    public String saveMortgageUpdate(@ModelAttribute("Mortgage") Mortgage mortgage, Model model){
        mortgageService.updateMortgage(mortgage);
        
        model.addAttribute("successMessage", "Mortgage saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteMortgage/{id}")
	public String deleteMortgage(@PathVariable Long id) {
		mortgageService.deleteMortgageById(id);
		return "redirect:/propertyList";
	}

    //QUITRENT
    @GetMapping("/formquitrent")
    public String formquitrent(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property quitRent = new QuitRent();
        quitRent.setUser(loggedInUser);

        model.addAttribute("QuitRent", quitRent);
        return "formQuitRent";
    }

    @PostMapping("/saveQuitRent")
    public String saveQuitRent(@ModelAttribute("QuitRent") QuitRent quitRent, Model model){
        Land existingLand = landService.getLandByTitleId(quitRent.getTitleId());

        if(existingLand != null){
            model.addAttribute("msg", "Land with Title Id is already exist.");

            return "formquitrent";
        }
        quitRentService.saveQuitRent(quitRent);

        return "redirect:/propertyList";
    }

    @GetMapping("/formquitrentupdate/{id}")
    public String formquitrentupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        QuitRent existingQuitRent = quitRentService.getQuitRentById(id);

        model.addAttribute("QuitRent", existingQuitRent);
        return "formQuitRentUpdate";
    }

    @PostMapping("/saveQuitRentUpdate")
    public String saveQuitRentUpdate(@ModelAttribute("QuitRent") QuitRent quitRent, Model model){
        quitRentService.updateQuitRent(quitRent);
        
        model.addAttribute("successMessage", "Quit Rent saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteQuitRent/{id}")
	public String deleteQuitRent(@PathVariable Long id) {
		quitRentService.deleteQuitRentById(id);
		return "redirect:/propertyList";
	}

    //RIGHTOFWAY
    @GetMapping("/formrightofway")
    public String formrightofway(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property rightOfWay = new RightOfWay();
        rightOfWay.setUser(loggedInUser);

        model.addAttribute("RightOfWay", rightOfWay);
        return "formRightOfWay";
    }

    @PostMapping("/saveRightOfWay")
    public String saveRightOfWay(@ModelAttribute("RightOfWay") RightOfWay rightOfWay, Model model){
        Land existingLand = landService.getLandByTitleId(rightOfWay.getTitleId());

        if(existingLand != null){
            model.addAttribute("msg", "Land with Title Id is already exist.");

            return "formrightofway";
        }
        rightOfWayService.saveRightOfWay(rightOfWay);

        return "redirect:/propertyList";
    }

    @GetMapping("/formrightofwayupdate/{id}")
    public String formrightofwayupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        RightOfWay existingRightOfWay = rightOfWayService.getRightOfWayById(id);

        model.addAttribute("RightOfWay", existingRightOfWay);
        return "formRightOfWayUpdate";
    }

    @PostMapping("/saveRightOfWayUpdate")
    public String saveRightOfWayUpdate(@ModelAttribute("RightOfWay") RightOfWay rightOfWay, Model model){
        rightOfWayService.updateRightOfWay(rightOfWay);
        
        model.addAttribute("successMessage", "Right Of Way saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteRightOfWay/{id}")
	public String deleteRightOfWay(@PathVariable Long id) {
		rightOfWayService.deleteRightOfWayById(id);
		return "redirect:/propertyList";
	}

    //TENANCY
    @GetMapping("/formtenancy")
    public String formtenancy(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property tenancy = new Tenancy();
        tenancy.setUser(loggedInUser);

        model.addAttribute("Tenancy", tenancy);
        return "formTenancy";
    }

    @PostMapping("/saveTenancy")
    public String saveTenancy(@ModelAttribute("Tenancy") Tenancy tenancy, Model model){
        Land existingLand = landService.getLandByTitleId(tenancy.getTitleId());

        if(existingLand != null){
            model.addAttribute("msg", "Land with Title Id is already exist.");

            return "formtenancy";
        }
        tenancyService.saveTenancy(tenancy);

        return "redirect:/propertyList";
    }

    @GetMapping("/formtenancyupdate/{id}")
    public String formtenancyupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Tenancy existingTenancy = tenancyService.getTenancyById(id);

        model.addAttribute("Tenancy", existingTenancy);
        return "formTenancyUpdate";
    }

    @PostMapping("/saveTenancyUpdate")
    public String saveTenancyUpdate(@ModelAttribute("Tenancy") Tenancy tenancy, Model model){
        tenancyService.updateTenancy(tenancy);
        
        model.addAttribute("successMessage", "Tenancy saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteTenancy/{id}")
	public String deleteTenancy(@PathVariable Long id) {
		tenancyService.deleteTenancyById(id);
		return "redirect:/propertyList";
	}

    //TRANSFER
    @GetMapping("/formtransfer")
    public String formtransfer(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property transfer = new Transfer();
        transfer.setUser(loggedInUser);

        model.addAttribute("Transfer", transfer);
        return "formTransfer";
    }

    @PostMapping("/saveTransfer")
    public String saveTransfer(@ModelAttribute("Transfer") Transfer transfer, Model model){
        Land existingLand = landService.getLandByTitleId(transfer.getTitleId());

        if(existingLand != null){
            model.addAttribute("msg", "Land with Title Id is already exist.");

            return "formtransfer";
        }

        transferService.saveTransfer(transfer);

        return "redirect:/propertyList";
    }

    @GetMapping("/formtransferupdate/{id}")
    public String formtransferupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Transfer existingTransfer = transferService.getTransferById(id);

        model.addAttribute("Transfer", existingTransfer);
        return "formTransferUpdate";
    }

    @PostMapping("/saveTransferUpdate")
    public String saveTransferUpdate(@ModelAttribute("Transfer") Transfer transfer, Model model){
        transferService.updateTransfer(transfer);
        
        model.addAttribute("successMessage", "Transfer saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteTransfer/{id}")
	public String deleteTransfer(@PathVariable Long id) {
		transferService.deleteTransferById(id);
		return "redirect:/propertyList";
	}

    //UTILITIESBILL
    @GetMapping("/formutilitiesbill")
    public String formutilitiesbill(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property utilitiesBill = new UtilitiesBill();
        utilitiesBill.setUser(loggedInUser);

        model.addAttribute("UtilitiesBill", utilitiesBill);
        return "formutilitiesbill";
    }

    @PostMapping("/saveUtilitiesBill")
    public String saveUtilitiesBill(@ModelAttribute("UtilitiesBill") UtilitiesBill utilitiesBill, Model model){
        Land existingLand = landService.getLandByTitleId(utilitiesBill.getTitleId());

        if(existingLand != null){
            model.addAttribute("msg", "Land with Title Id is already exist.");

            return "formutilitiesbill";
        }

        utilitiesBillService.saveUtilitiesBill(utilitiesBill);

        return "redirect:/propertyList";
    }

    @GetMapping("/formutilitiesbillupdate/{id}")
    public String formutilitiesbillupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        UtilitiesBill existingUtilitiesBill = utilitiesBillService.getUtilitiesBillById(id);

        model.addAttribute("UtilitiesBill", existingUtilitiesBill);
        return "formUtilitiesBillUpdate";
    }

    @PostMapping("/saveUtilitiesBillUpdate")
    public String saveUtilitiesBillUpdate(@ModelAttribute("UtilitiesBill") UtilitiesBill utilitiesBill, Model model){
        utilitiesBillService.updateUtilitiesBill(utilitiesBill);
        
        model.addAttribute("successMessage", "Utilities Bill saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteUtilitiesBill/{id}")
	public String deleteUtilitiesBill(@PathVariable Long id) {
		utilitiesBillService.deleteUtilitiesBillById(id);
		return "redirect:/propertyList";
	}

    //WAQF
    @GetMapping("/formwaqf")
    public String formWaqf(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property waqf = new Waqf();
        waqf.setUser(loggedInUser);

        model.addAttribute("Waqf", waqf);
        return "formWaqf";
    }

    @PostMapping("/saveWaqf")
    public String saveWaqf(@ModelAttribute("Waqf") Waqf waqf, Model model){
        Land existingLand = landService.getLandByTitleId(waqf.getTitleId());

        if(existingLand != null){
            model.addAttribute("msg", "Land with Title Id is already exist.");

            return "formwaqf";
        }
        waqfService.saveWaqf(waqf);

        return "redirect:/propertyList";
    }

    @GetMapping("/formwaqfupdate/{id}")
    public String formwaqfupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Waqf existingWaqf = waqfService.getWaqfById(id);

        model.addAttribute("Waqf", existingWaqf);
        return "formWaqfUpdate";
    }

    @PostMapping("/saveWaqfUpdate")
    public String saveWaqfUpdate(@ModelAttribute("Waqf") Waqf waqf, Model model){
        waqfService.updateWaqf(waqf);
        
        model.addAttribute("successMessage", "Waqf saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteWaqf/{id}")
	public String deleteWaqf(@PathVariable Long id) {
		waqfService.deleteWaqfById(id);
		return "redirect:/propertyList";
	}

    //CASH
    @GetMapping("/formcash")
    public String formcash(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property cash = new Cash();
        cash.setUser(loggedInUser);

        model.addAttribute("Cash", cash);
        return "formcash";
    }

    @PostMapping("/saveCash")
    public String saveCash(@ModelAttribute("Cash") Cash cash){
        cashService.saveCash(cash);

        return "redirect:/propertyList";
    }

    @GetMapping("/formcashupdate/{id}")
    public String formcashupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Cash existingCash = cashService.getCashById(id);

        model.addAttribute("Cash", existingCash);
        return "formCashUpdate";
    }

    @PostMapping("/saveCashUpdate")
    public String saveCashUpdate(@ModelAttribute("Cash") Cash cash, Model model){
        cashService.updateCash(cash);
        
        model.addAttribute("successMessage", "Cash saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteCash/{id}")
	public String deleteCash(@PathVariable Long id) {
		cashService.deleteCashById(id);
		return "redirect:/propertyList";
	}

    //DEBENTURE
    @GetMapping("/formdebenture")
    public String formdebenture(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property debenture = new Debenture();
        debenture.setUser(loggedInUser);

        model.addAttribute("Debenture", debenture);
        return "formDebenture";
    }

    @PostMapping("/saveDebenture")
    public String saveDebenture(@ModelAttribute("Debenture") Debenture debenture){
        debentureService.saveDebenture(debenture);

        return "redirect:/propertyList";
    }

    @GetMapping("/formdebentureupdate/{id}")
    public String formdebentureupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Debenture existingDebenture = debentureService.getDebentureById(id);

        model.addAttribute("Debenture", existingDebenture);
        return "formDebentureUpdate";
    }

    @PostMapping("/saveDebentureUpdate")
    public String saveDebentureUpdate(@ModelAttribute("Debenture") Debenture debenture, Model model){
        debentureService.updateDebenture(debenture);
        
        model.addAttribute("successMessage", "Debenture saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteDebenture/{id}")
	public String deleteDebenture(@PathVariable Long id) {
		debentureService.deleteDebentureById(id);
		return "redirect:/propertyList";
	}

    //INSURANCE
    @GetMapping("/forminsurance")
    public String forminsurance(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property insurance = new Insurance();
        insurance.setUser(loggedInUser);

        model.addAttribute("Insurance", insurance);
        return "forminsurance";
    }

    @PostMapping("/saveInsurance")
    public String saveInsurance(@ModelAttribute("Insurance") Insurance insurance){
        insuranceService.saveInsurance(insurance);

        return "redirect:/propertyList";
    }

    @GetMapping("/forminsuranceupdate/{id}")
    public String forminsuranceupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Insurance existingInsurance = insuranceService.getInsuranceById(id);

        model.addAttribute("Insurance", existingInsurance);
        return "formInsuranceUpdate";
    }

    @PostMapping("/saveInsuranceUpdate")
    public String saveInsuranceUpdate(@ModelAttribute("Insurance") Insurance insurance, Model model){
        insuranceService.updateInsurance(insurance);
        
        model.addAttribute("successMessage", "Insurance saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteInsurance/{id}")
	public String deleteInsurance(@PathVariable Long id) {
		insuranceService.deleteInsuranceById(id);
		return "redirect:/propertyList";
	}

    //SHARE
    @GetMapping("/formshare")
    public String formshare(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property share = new Share();
        share.setUser(loggedInUser);

        model.addAttribute("Share", share);
        return "formShare";
    }

    @PostMapping("/saveShare")
    public String saveShare(@ModelAttribute("Share") Share share){
        shareService.saveShare(share);

        return "redirect:/propertyList";
    }

    @GetMapping("/formshareupdate/{id}")
    public String formshareupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Share existingShare = shareService.getShareById(id);

        model.addAttribute("Share", existingShare);
        return "formShareUpdate";
    }

    @PostMapping("/saveShareUpdate")
    public String saveShareUpdate(@ModelAttribute("Share") Share share, Model model){
        shareService.updateShare(share);
        
        model.addAttribute("successMessage", "Share saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteShare/{id}")
	public String deleteShare(@PathVariable Long id) {
		shareService.deleteShareById(id);
		return "redirect:/propertyList";
	}

    //UNITTRUST
    @GetMapping("/formunittrust")
    public String formunittrust(Model model){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property unitTrust = new UnitTrust();
        unitTrust.setUser(loggedInUser);

        model.addAttribute("UnitTrust", unitTrust);
        return "formUnitTrust";
    }

    @PostMapping("/saveUnitTrust")
    public String saveUnitTrust(@ModelAttribute("UnitTrust") UnitTrust unitTrust){
        unitTrustService.saveUnitTrust(unitTrust);

        return "redirect:/propertyList";
    }

    @GetMapping("/formunittrustupdate/{id}")
    public String formunittrust(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        UnitTrust existingUnitTrust = unitTrustService.getUnitTrustById(id);

        model.addAttribute("UnitTrust", existingUnitTrust);
        return "formUnitTrustUpdate";
    }

    @PostMapping("/saveUnitTrustUpdate")
    public String saveUnitTrustUpdate(@ModelAttribute("UnitTrust") UnitTrust unitTrust, Model model){
        unitTrustService.updateUnitTrust(unitTrust);
        
        model.addAttribute("successMessage", "Unit Trust saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteUnitTrust/{id}")
	public String deleteUnitTrust(@PathVariable Long id) {
		unitTrustService.deleteUnitTrustById(id);
		return "redirect:/propertyList";
	}

    //VEHICLE
    @GetMapping("/formvehicle")
    public String formvehicle(Model model,
    @RequestParam(name="transactionHash", required = false) String transactionHash){
        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);

        Property vehicle = new Vehicle();
        vehicle.setUser(loggedInUser);

        model.addAttribute("Vehicle", vehicle); 
        model.addAttribute("transactionHash", transactionHash); 
        return "formVehiclecontract";
    }

    @PostMapping("/formvehicle/saveTransactionHash")
    @ResponseBody
    public String saveTransactionHash(
            @RequestParam String transactionHash) {
                try {
                    // Save the transaction hash with the user-provided ID
                    contractService.saveContract(transactionHash, "");
                    return "Transaction hash saved successfully!";
                } catch (Exception e) {
                    return "Failed to save transaction hash: " + e.getMessage();
                }
            }

            @GetMapping("/view/formvehicle")
            public String viewformvehiclecontract(Model model,
            @RequestParam(name="transactionHash", required = false) String transactionHash){
                User loggedInUser = getLoggedInUser();
        
                Long userId = loggedInUser.getId();
                model.addAttribute("userId", userId);
        
                Property vehicle = new Vehicle();
                vehicle.setUser(loggedInUser);
        
                model.addAttribute("Vehicle", vehicle); 
                 model.addAttribute("userId", userId);
                model.addAttribute("transactionHash", transactionHash); 
                return "viewvehicleformcontract";
            }

    // @GetMapping("/formvehicle")
    // public String formvehicle(Model model){
    //     User loggedInUser = getLoggedInUser();

    //     Long userId = loggedInUser.getId();
    //     model.addAttribute("userId", userId);

    //     Property vehicle = new Vehicle();
    //     vehicle.setUser(loggedInUser);

    //     model.addAttribute("Vehicle", vehicle);
    //     return "formVehicle";
    // }

    // @PostMapping("/saveVehicle")
    // public String saveVehicle(@ModelAttribute("Vehicle") Vehicle vehicle, Model model){
    //     List<Vehicle> vehicles = vehicleService.getAllVehicles();

    //     for (Vehicle vehicleitem : vehicles) {
    //         if(vehicle.getCarRegNum().equals(vehicleitem.getCarRegNum())){
    //             model.addAttribute("msg", "Vehicle with registration number is existed.");
    //             return "formVehicle";
    //         }
    //     }
        
    //     vehicleService.saveVehicle(vehicle);

    //     return "redirect:/propertyList";
    // }

    @GetMapping("/formvehicleupdate/{id}")
    public String formvehicleupdate(@PathVariable Long id, Model model){

        User loggedInUser = getLoggedInUser();

        Long userId = loggedInUser.getId();
        model.addAttribute("userId", userId);
        
        Vehicle exisitngVehicle = vehicleService.getVehicleById(id);

        model.addAttribute("Vehicle", exisitngVehicle);
        return "formvehicleupdate";
    }

    @PostMapping("/saveVehicleUpdate")
    public String saveVehicleUpdate(@ModelAttribute("Vechile") Vehicle vehicle, Model model){
        vehicleService.updateVehicle(vehicle);
        
        model.addAttribute("successMessage", "Vehicle saved successfully!");

        return "redirect:/propertyList";
    }

    @GetMapping("/deleteVehicle/{id}")
	public String deleteVehicle(@PathVariable Long id) {
		vehicleService.deleteVehicleById(id);
		return "redirect:/propertyList";
	}

}
