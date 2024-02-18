package com.unicam.risorseLocali.Core.Repository;

import com.unicam.risorseLocali.Core.Model.Entity.Richiesta;
import com.unicam.risorseLocali.Util.Enum.StatusRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface RichiestaRepository<T extends Richiesta> extends JpaRepository<T, String> {

    List<T> findByIdAccount(String username);

    List<T> findByStato(StatusRequest stato);

}
