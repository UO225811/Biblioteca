package com.capgemini.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.model.Copia;
import com.capgemini.repository.CopiaRepository;
import com.capgemini.service.CopiaService;

@Service
public class CopiaServiceImpl implements CopiaService{

	@Autowired
	private CopiaRepository copiaRepository;

	@Override
	public Copia getCopyById(long id) {
		Optional<Copia> opt = copiaRepository.findById(id);
		Copia copia = null;
		if (opt.isPresent())
			copia = opt.get();
		else
			throw new RuntimeException("Copy with id " + id + " not found");
		return copia;
	}

	@Override
	public List<Copia> getAllCopies() {
		return copiaRepository.findAll();
	}

	@Override
	public void saveCopy(Copia c) {
		copiaRepository.save(c);
	}

	@Override
	public void deleteCopyById(long id) {
		copiaRepository.deleteById(id);		
	}

	@Override
	public int getNumberOfAvailableCopiesById(long id) {
		return copiaRepository.getNumCopiasByLibroId(id);
	}

	@Override
	public List<Copia> findCopiasByLibroId(long libroId) {
		return copiaRepository.findCopiasByLibroId(libroId);
	}

	@Override
	public Page<Copia> findPaginated(long libroId, int pageNum, int pageSize, String sortField, String sortOrder) {
		Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNum-1, pageSize, sort);

		List<Copia> copias = findCopiasByLibroId(libroId);
		
		return new PageImpl<Copia>(copias, pageable, copias.size());
	} 
}
