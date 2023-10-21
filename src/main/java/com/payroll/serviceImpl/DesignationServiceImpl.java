package com.payroll.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.entities.Designation;
import com.payroll.repository.DesignationRepository;
import com.payroll.service.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService{

	@Autowired
	DesignationRepository designationRepository;
	
	@Override
	public boolean addDesignation(Designation designation) {
		
		boolean flag=validateDesignation(designation.getDesignationId());
		
		if(!flag)
		{
			Designation save = designationRepository.save(designation);
			
			if(save.toString().equals(designation.toString()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		return false;
	}

	@Override
	public boolean deleteDesignation(int designationId) {
		
		Optional<Designation> optional = designationRepository.findById(designationId);
		
		if(optional.isPresent())
		{
			designationRepository.delete(optional.get());
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateDesignation(Designation designation) {
		
		Designation save = designationRepository.save(designation);
		
		if(save!=null)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public List<Designation> getAllDesignations() {
		
		
		return designationRepository.findAll();
	}

	@Override
	public Designation getDesignation(int designationId) {
		
		Optional<Designation> optional = designationRepository.findById(designationId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@Override
	public Designation getDesignation(String designationName) {
		Designation designation = designationRepository.getDesignationBydesignationName(designationName);
		return designation;
	}
	public boolean validateDesignation(int id)
	{
		Optional<Designation> optional = designationRepository.findById(id);
		
		if(optional.isPresent())
		{
			return true;
		}
		
		return false;
		
	}

}
