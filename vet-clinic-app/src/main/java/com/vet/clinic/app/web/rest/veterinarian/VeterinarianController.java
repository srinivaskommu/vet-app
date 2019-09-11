package com.vet.clinic.app.web.rest.veterinarian;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.vet.clinic.app.domain.veterinarian.Veterinarian;
import com.vet.clinic.app.domain.veterinarian.VeterinarianRepository;
import com.vet.clinic.app.web.rest.errors.VeterinarianNotFoundException;
import lombok.extern.slf4j.Slf4j;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
@Validated
public class VeterinarianController {

  @Autowired
  private VeterinarianRepository veterinarianRepository;

  @GetMapping("/veterinarians")
  public Iterable<Veterinarian> getAll(Pageable pageable) {
    return veterinarianRepository.findAll();
  }

  @GetMapping("/veterinarians/{id}")
  public ResponseEntity<Veterinarian> getVeterinarian(@PathVariable Long id) {
    log.debug("REST request to get Employee : {}", id);
    Optional<Veterinarian> veterinarian = veterinarianRepository.findById(id);
    return ok(veterinarian.orElseThrow(() -> new VeterinarianNotFoundException()));
  }

  @PostMapping("/veterinarians")
  public ResponseEntity<Veterinarian> createVeternarian(@RequestBody Veterinarian veterinarian,
      HttpServletRequest request)
      throws URISyntaxException {
    log.debug("REST request to save Veternarian : {}", veterinarian);

    Veterinarian result = veterinarianRepository.save(veterinarian);
    
    return created(
        ServletUriComponentsBuilder
            .fromContextPath(request)
            .path("/veterinarians/{id}")
            .buildAndExpand(result.getId())
            .toUri())
        .build();
    
//    return ResponseEntity
//        .created(new URI("/veternarians/" + result.getId())).headers(HeaderUtil
//            .createEntityCreationAlert("vsp", false, "Veterinarian", result.getId().toString()))
//        .body(result);
  }

//  @PutMapping("/veterinarians/{id}")
//  public ResponseEntity<Veterinarian> updateVeternarian(@RequestBody Veterinarian veterinarian,
//      @PathVariable Long id) throws URISyntaxException {
//    log.debug("REST request to update Veternarian : {}", veterinarian);
//
//    Veterinarian result = veterinarianRepository.save(veterinarian);
//    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert("vsp", false,
//        "Veterinarian", veterinarian.getId().toString())).body(result);
//  }

//  @DeleteMapping("/veterinarians/{id}")
//  public ResponseEntity<Veterinarian> deleteVeternarian(@PathVariable Long id)
//      throws URISyntaxException {
//
//    veterinarianRepository.deleteById(id);
//
//    return ResponseEntity.noContent()
//        .headers(HeaderUtil.createEntityDeletionAlert("vsp", false, "Veterinarian", id.toString()))
//        .build();
//  }

}
