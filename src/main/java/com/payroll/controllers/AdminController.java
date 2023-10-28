package com.payroll.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.payroll.entities.Attendence;
import com.payroll.entities.Department;
import com.payroll.entities.Designation;
import com.payroll.entities.Employee;
import com.payroll.entities.Leaves;
import com.payroll.entities.OverTimeAttendence;
import com.payroll.entities.Salary;
import com.payroll.excepitons.DepartmentException;
import com.payroll.excepitons.DesignationException;
import com.payroll.excepitons.EmployeeException;
import com.payroll.service.AttendenceService;
import com.payroll.service.DepartmentService;
import com.payroll.service.DesignationService;
import com.payroll.service.EmployeeService;
import com.payroll.service.LeavesService;
import com.payroll.service.OverTimeAttendenceService;
import com.payroll.service.SalaryService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {

	// should update employee,attendence.
	@Autowired
	DepartmentService departmentService;

	@Autowired
	Department department;

	@Autowired
	Employee employee;

	@Autowired
	Attendence attendence;

	@Autowired
	Salary salary;

	@Autowired
	OverTimeAttendence overTimeAttendence;

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
			throw new DesignationException("Invalid Designation Details", "Adding Designation");
		}
		designation.setDesignationName(designation.getDesignationName().toLowerCase());
		Designation designationWithName = designationService.getDesignationWithName(designation.getDesignationName());
		if (designationWithName != null) {
			throw new DesignationException("Already " + designation.getDesignationName() + " exist :",
					"while Adding designation name");
		}
		if (designationService.addDesignation(designation)) {
			model.addAttribute("submitOk", "Designation added successfully");
			model.addAttribute("obj", this.designation);
		} else {
			model.addAttribute("obj", designation);
		}
		model.addAttribute("url", "/admin/addDesignationDetails");
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
		if (designationWithName != null) {
			throw new DesignationException("Already " + designation.getDesignationName() + " exist :",
					"Updating designation name");
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

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/addEmployee")
	public ModelAndView addEmp(Model model) {
		model.addAttribute("indexName", "Add Employee");
		model.addAttribute("currentDesc", "Add Employee :-");
		model.addAttribute("obj", employee);
		model.addAttribute("showFileView",true);
		model.addAttribute("url", "/admin/addEmployeeDetails");
		return new ModelAndView("admin/addEmployee");
	}

	@PostMapping("/addEmployeeDetails")
	public ModelAndView addDetailsOfEmployee(Model model, MultipartFile file, @ModelAttribute Employee employee,
			@ModelAttribute Department department, @ModelAttribute Designation designation) throws IOException {
		Designation desigObj = designationService.getDesignation(designation.getDesignationName());
		if (!departmentService.validateDepartment(department.getDepartmentId())) {
			throw new DepartmentException("No department Exist with the id:" + department.getDepartmentId(),
					" Occured while adding Employee");
		}
		if (!employee.isValidDetails()) {
			throw new EmployeeException("Please enter valid Employee Details : ",
					"Occured while adding employee details");
		}
		if (file.isEmpty()) {
			throw new EmployeeException("Please add Employee Image and size should be < 1MB",
					" Occured while adding Employee");
		} else if ((file.getContentType().equals("image/jpg") || file.getContentType().equals("image/jpeg")
				|| file.getContentType().equals("image/png")) && (file.getSize() < 1000000)) {

			String fileName = employee.getEmployeeId() + "_" + file.getOriginalFilename();
			File saveFile = null;
			try {
				saveFile = new ClassPathResource("/static/employee-images").getFile();
			} catch (FileNotFoundException f) {
				File f1 = new ClassPathResource("/static").getFile();
				File f2 = new File(f1.getAbsolutePath() + File.separator + "employee-images");
				if (!f2.mkdir()) {
					throw new EmployeeException("Error Occured Try again !!!", " Occured while adding Employee");
				}
				saveFile = f2;
			}
			Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileName);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			employee.setEmployeePic(fileName);
		} else {
			throw new EmployeeException("File size should be less than 1MB.", " Occured while adding Employee");
		}
		employee.setEmployeeId(employee.getEmployeeId().toLowerCase());
		boolean isAdded = employeeService.addEmployee(employee, department.getDepartmentId(),
				desigObj.getDesignationName());
		if (isAdded) {
			model.addAttribute("submitOk", "Employee added successfully");
			model.addAttribute("obj", this.employee);
		} else {
			model.addAttribute("obj", employee);
		}
		return addEmp(model);
	}

	@GetMapping("/manageEmployee")
	public ModelAndView manageEmp(Model model) {
		List<Employee> employees = employeeService.getEmployees();
		if (employees.size() > 0) {
			int[] arr = new int[employees.size()];
			Random r = new Random();
			for (int i = 0; i < arr.length; i++) {
				arr[i] = r.nextInt(100);
			}
			model.addAttribute("numbers", arr);
			model.addAttribute("listOfEmployees", employees);
		}
		model.addAttribute("indexName", "Manage Employees");
		return new ModelAndView("admin/manageEmployee");
	}

	@GetMapping("/deleteEmployee/{id}")
	public ModelAndView delEmp(@PathVariable String id, Model model) throws IOException {
		if (employeeService.deleteEmployee(id)) {
			model.addAttribute("submitOk", "Employee Deleted Successfully");
		}
		return manageEmp(model);
	}

	@PostMapping("/editEmployeeDetails/{id}")
	public ModelAndView editEmpDetails(Model model, @PathVariable String id) {
		Employee employee2 = employeeService.getEmployee(id);
		model.addAttribute("obj", employee2);
		model.addAttribute("indexName", "Edit Employee");
		model.addAttribute("condition", "true");
		model.addAttribute("currentDesc", "Edit Employee :-");
		model.addAttribute("url", "/admin/updateDetails");
		model.addAttribute("addPicDescription", "true");
		model.addAttribute("designationName", employee2.getDesignation().getDesignationName());
		model.addAttribute("departmentId", employee2.getDepartment().getDepartmentId());
		return new ModelAndView("admin/addEmployee");
	}
	
	
	@PostMapping("/updateDetails")
	public ModelAndView updateDetails(Model model,@ModelAttribute Employee employee){
		if(employeeService.updateEmployee(employee)) {
			model.addAttribute("submitOk", "Employee details updated successfully");
		}else {
			model.addAttribute("message","Error occured while updation Try Again!!!");
		}
		return addEmp(model);
	}

	@Autowired
	AttendenceService attendenceService;

	@GetMapping("/addAttendence")
	public ModelAndView addAtttendencePage(Model model) {
		List<Employee> employees = employeeService.getEmployees();
		if (employees.size() > 0) {
			model.addAttribute("listOfEmployees", employees);
		}
		model.addAttribute("indexName", "Add Attendence");
		return new ModelAndView("admin/addAttendence");
	}

	@PostMapping("/addAttendenceDetails")
	public ModelAndView addAttendenceDetails(Model model, HttpServletRequest request) {
		List<Employee> employees = employeeService.getEmployees();
		for (Employee e : employees) {
			String checked = request.getParameter(e.getEmployeeId());
			String attendeceDate = request.getParameter(e.getDateOfBirth());
			Attendence attendence = new Attendence();
			if (checked != null) {
				attendence.setAttendanceStatus("P");
			} else {
				attendence.setAttendanceStatus("A");
			}
			attendence.setDate(attendeceDate);
			attendence.setEmployeeAttendence(e);
			e.getAttendence().add(attendence);
			employeeService.addEmployeeWithoutAnyCheck(e);
		}
		model.addAttribute("submitOk", "Attendence marked successfully !!!");
		return addAtttendencePage(model);
	}

	@GetMapping("/manageAttendence")
	public ModelAndView manageAttendence(Model model) {
		List<Attendence> allAttendences = attendenceService.getAllAttendences();
		if (allAttendences.size() > 0) {
			model.addAttribute("listOfAttendence", allAttendences);
		}
		model.addAttribute("indexName", "Manage Attendence");
		return new ModelAndView("admin/manageAttendence");
	}

	@GetMapping("/deleteAttendence/{id}")
	public ModelAndView delAttendence(@PathVariable int id, Model model) {
		if (attendenceService.deleteAttendence(id)) {
			model.addAttribute("submitOk", "Attendence Deleted Successfully");
		}
		return manageAttendence(model);
	}

	@Autowired
	SalaryService salaryService;

	@GetMapping("/addSalary")
	public ModelAndView addSal(Model model) {
		model.addAttribute("indexName", "Add Salary");
		model.addAttribute("currentDesc", "Add Salary / Manage Salary :-");
		model.addAttribute("obj", salary);
		model.addAttribute("designationId", "");
		model.addAttribute("url", "/admin/addSalaryDetails");
		return new ModelAndView("admin/addSalary");
	}

	@PostMapping("/addSalaryDetails")
	public ModelAndView addSalDetails(@ModelAttribute Designation designation, @ModelAttribute Salary sal,
			Model model) {
		Designation designation2 = designationService.getDesignation(designation.getDesignationId());
		designation2.setSalary(sal);
		if (salaryService.addSalary(sal, designation2)) {
			model.addAttribute("submitOk", "Designation added successfully");
			model.addAttribute("obj", this.salary);
		} else {
			model.addAttribute("obj", sal);
		}
		return addSal(model);
	}

	@GetMapping("/manageSalary")
	public ModelAndView manageSal(Model model) {
		List<Salary> allSalary = salaryService.getAllSalary();
		if (allSalary.size() > 0) {
			model.addAttribute("listOfSalary", allSalary);
		}
		model.addAttribute("indexName", "Manage Salaries");
		return new ModelAndView("admin/manageSalary");
	}

	@GetMapping("/deleteSalary/{id}")
	public ModelAndView delSal(@PathVariable int id, Model model) {
		if (salaryService.deleteSalary(id)) {
			model.addAttribute("submitOk", "Salary Deleted Successfully");
		}
		return manageSal(model);
	}

	@Autowired
	OverTimeAttendenceService overTimeAttendenceService;

	@GetMapping("/addOverTimeAttendence")
	public ModelAndView addoverTimeAttendence(Model model) {
		model.addAttribute("indexName", "Add Overtime Attendence");
		model.addAttribute("currentDesc", "Add Overtime Attendence :-");
		model.addAttribute("obj", overTimeAttendence);
		model.addAttribute("employeeId", "");
		model.addAttribute("url", "/admin/addOvertimeAttendeneDetails");
		return new ModelAndView("admin/addOverTimeAttendence");
	}

	@PostMapping("/addOvertimeAttendeneDetails")
	public ModelAndView addOAttendenceDetails(@ModelAttribute Employee employee,
			@ModelAttribute OverTimeAttendence attendence,Model model) {
		if (overTimeAttendenceService.addOverTimeAttendence(attendence, employee.getEmployeeId())) {
			model.addAttribute("submitOk", "Department added successfully");
			model.addAttribute("obj", this.overTimeAttendence);
			model.addAttribute("employeeId", "");
		} else {
			model.addAttribute("obj", attendence);
			model.addAttribute("employeeId",employee.getEmployeeId());
		}
		model.addAttribute("indexName", "Add Overtime Attendence");
		model.addAttribute("currentDesc", "Add Overtime Attendence :-");
		model.addAttribute("url", "/admin/addOvertimeAttendeneDetails");
		return new ModelAndView("admin/addOverTimeAttendence");
	}

	@GetMapping("/manageOverTimeAttendence")
	public ModelAndView manageoverTimeAttendence(Model model) {
		List<OverTimeAttendence> attendenece = overTimeAttendenceService.getAllOvertimeAttendenceDetails();
		if (attendenece.size() > 0) {
			model.addAttribute("listOfAttendences", attendenece);
		}
		model.addAttribute("indexName", "Manage OverTime Attendence");
		return new ModelAndView("admin/manageOverTimeAttendence");
	}
	
	@GetMapping("/deleteOvertimeAttendence/{id}")
	public ModelAndView delOvertimeAttendence(@PathVariable int id, Model model) {
		if (overTimeAttendenceService.deleteOverTimeAttendence(id)) {
			model.addAttribute("submitOk", "Salary Deleted Successfully");
		}
		return manageoverTimeAttendence(model);
	}
	
	@Autowired
	LeavesService leavesService;
	
	@GetMapping("/showLeaves")
	public ModelAndView getLeaves(Model model) {
		List<Leaves> allLeaves = leavesService.getAllLeaves();
		if (allLeaves.size() > 0) {
			model.addAttribute("listOfLeaves", allLeaves);
		}
		model.addAttribute("indexName", "Manage leaves");
		return new ModelAndView("admin/manageLeaves");
	}
	
	@PostMapping("/acceptLeave/{id}")
	public ModelAndView updateLeave(@PathVariable int id,Model model) {
		if(leavesService.updateLeave(id)) {
			model.addAttribute("submitOk", "Leave Granted Successfuly");
		}
		return getLeaves(model);
	}
	
	@GetMapping("/deleteLeave/{id}")
	public ModelAndView deleteLeave(@PathVariable int id,Model model) {
		if(leavesService.deleteLeave(id)) {
			model.addAttribute("submitOk", "Leave Rejected Successfuly");
		}
		return getLeaves(model);
	}
}
