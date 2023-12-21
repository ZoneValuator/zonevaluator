package fr.esgi.zonevaluator.repository;

import fr.esgi.zonevaluator.business.Pdf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PdfRepository extends JpaRepository<Pdf, Long> {}