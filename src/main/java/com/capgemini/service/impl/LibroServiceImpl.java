package com.capgemini.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.model.Libro;
import com.capgemini.repository.LibroRepository;
import com.capgemini.service.LibroService;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroRepository libroRepository;

	@Override
	public Libro getBookById(long id) {
		Optional<Libro> opt = libroRepository.findById(id);
		Libro libro = null;
		if (opt.isPresent())
			libro = opt.get();
		else
			throw new RuntimeException("Book with id " + id + " not found");
		return libro;
	}

	@Override
	public List<Libro> getAllBooks() {
		return libroRepository.findAll();
	}

	@Override
	public void saveBook(Libro l) {
		libroRepository.save(l);
	}

	@Override
	public void deleteBookById(long id) {
		libroRepository.deleteById(id);
	}

	@Override
	public Page<Libro> findPaginated(int pageNum, int pageSize, String sortField, String sortOrder) {
		Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);

		return this.libroRepository.findAll(pageable);
	}

}
