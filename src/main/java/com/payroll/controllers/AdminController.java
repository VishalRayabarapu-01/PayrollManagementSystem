package com.payroll.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.entities.Department;
import com.payroll.entities.Designation;
import com.payroll.excepitons.DepartmentException;
import com.payroll.excepitons.DesignationException;
import com.payroll.service.DepartmentService;
import com.payroll.service.DesignationService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	DepartmentService departmentService;

	@Autowired
	Department department;

	@GetMapping("/index")
	public ModelAndView viewHome(Model model) {
		model.addAttribute("indexName", "Admin Home");
		return new ModelAndView("admin/index");
	}

	@GetMapping("/addDepartment")
	public ModelAndView adddept(Model model) {
		model.addAttribute("indexName", "Add department");
		model.addAttribute("currentDesc", "Add Department :-");
		model.addAttribute("obj", department);
		model.addAttribute("url", "/admin/addDepartmentDetails");
		return new ModelAndView("admin/addDepartment");
	}

	@PostMapping("/addDepartmentDetails")
	public ModelAndView addDeptDetails(@ModelAttribute Department department, Model model) {
		if (department.getDepartmentId() == 0 || department.getDepartmentName().equals("")) {
			throw new DepartmentException("Invalid Department Details", "Adding Department");
		}
		
		department.setDepartmentName(department.getDepartmentName().toLowerCase());
		if (departmentService.addDepartment(department)) {
			model.addAttribute("submitOk", "Department added successfully");
			model.addAttribute("obj", this.department);
		} else {
			model.addAttribute("obj", department);
		}
		model.addAttribute("url", "/admin/addDepartmentDetails");
		model.addAttribute("indexName", "Add department");
		model.addAttribute("currentDesc", "Add Department :-");
		return new ModelAndView("admin/addDepartment");
	}

	@GetMapping("/manageDepartment")
	public ModelAndView viewDept(Model model) {
		List<Department> allDepartments = departmentService.getAllDepartments();
		if (allDepartments.size() > 0) {
			model.addAttribute("listOfDepartments", allDepartments);
		}
		model.addAttribute("indexName", "Manage departments");
		return new ModelAndView("admin/manageDepartment");
	}

	@PostMapping("/editDepartment/{id}")
	public ModelAndView editDetails(@PathVariable int id, Model model) {
		model.addAttribute("obj", departmentService.getDepartment(id));
		model.addAttribute("indexName", "Edit department");
		model.addAttribute("condition", "true");
		model.addAttribute("currentDesc", "Edit Department :-");
		model.addAttribute("url", "/admin/editDepartmentDetails");
		return new ModelAndView("admin/addDepartment");
	}

	@PostMapping("/editDepartmentDetails")
	public ModelAndView editDeptDetails(@ModelAttribute Department department, Model model) {
		if (departmentService.updateDepartment(department)) {
			model.addAttribute("submitOk", "Department updated successfully");
			model.addAttribute("obj", this.department);
		} else {
			model.addAttribute("obj", department);
		}
		model.addAttribute("indexName", "Add department");
		model.addAttribute("currentDesc", "Add Department :-");
		return new ModelAndView("admin/addDepartment");
	}

	@GetMapping("/deleteDepartment/{num}")
	public ModelAndView deleteDepartment(@PathVariable int num, Model model) {
		if (departmentService.deleteDepartment(num)) {
			model.addAttribute("submitOk", "Deleted Successfully");
		} else {
			model.addAttribute("message", "Error occured while Deleting Try Again!!!");
		}
		return viewDept(model);
	}

	@Autowired
	DesignationService designationService;

	@Autowired
	Designation designation;

	@GetMapping("/addDesignation")
	public ModelAndView addDesig(Model model) {
		model.addAttribute("indexName", "Add Designation");
		model.addAttribute("currentDesc", "Add Designation :-");
		model.addAttribute("obj", designation);
		model.addAttribute("url", "/admin/addDesignationDetails");
		return new ModelAndView("admin/addDesignation");
	}

	@PostMapping("/addDesignationDetails/{id}")
	public ModelAndView addDesigDetails(@ModelAttribute Designation designation, Model model) {
		if (designation.getDesignationName().equals("")) {
			throw new DepartmentException("Invalid Designation Details", "Adding Designation");
		}
		designation.setDesignationName(designation.getDesignationName().toLowerCase());
		if (designationService.addDesignation(designation)) {
			model.addAttribute("submitOk", "Designation added successfully");
			model.addAttribute("obj", this.designation);
		} else {
			model.addAttribute("obj", designation);
		}
		model.addAttribute("url","/admin/addDesignationDetails");
		model.addAttribute("indexName", "Add Designation");
		model.addAttribute("currentDesc", "Add Designation :-");
		return new ModelAndView("admin/addDesignation");
	}

	@GetMapping("/manageDesignation")
	public ModelAndView manageDesig(Model model) {
		List<Designation> allDesignations = designationService.getAllDesignations();
		if (allDesignations.size() > 0) {
			model.addAttribute("listOfDesignations", allDesignations);
		}
		model.addAttribute("indexName", "Manage Designations");
		return new ModelAndView("admin/manageDesignation");
	}

	@PostMapping("/editDesignation/{id}")
	public ModelAndView editDesigDetails(@PathVariable int id, Model model) {
		model.addAttribute("obj", designationService.getDesignation(id));
		model.addAttribute("indexName", "Edit Designation");
		model.addAttribute("currentDesc", "Edit Designation :-");
		model.addAttribute("url", "/admin/editDesignationDetails");
		return new ModelAndView("admin/addDesignation");
	}

	@PostMapping("/editDesignationDetails/{id}")
	public ModelAndView editDesigDetails(@PathVariable int id, @ModelAttribute Designation designation, Model model) {
		Designation designationWithName = designationService.getDesignationWithName(designation.getDesignationName());
		if(designationWithName!=null) {
			throw new DesignationException("Already "+designation.getDesignationName()+" exist :","Updating designation name");
		}
		designation.setDesignationId(id);
		if (designationService.updateDesignation(designation)) {
			model.addAttribute("submitOk", "Designation updated successfully");
			model.addAttribute("obj", this.designation);
		} else {
			model.addAttribute("obj", designation);
			model.addAttribute("message", "Some internal issues occured Try Again!!!");
		}
		model.addAttribute("indexName", "Add Designation");
		model.addAttribute("currentDesc", "Add Designation :-");
		return new ModelAndView("admin/addDesignation");
	}

	@GetMapping("/deleteDesignation/{num}")
	public ModelAndView deleteDesig(@PathVariable int num, Model model) {
		if (designationService.deleteDesignation(num)) {
			model.addAttribute("submitOk", "Deleted Successfully");
		} else {
			model.addAttribute("message", "Error occured while Deleting Try Again!!!");
		}
		return manageDesig(model);
	}
}
