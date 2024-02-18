package com.unicam.risorseLocali.Core.Model.Interface;

import com.unicam.risorseLocali.Util.Enum.StatusElement;

public interface ElementiMappa {
    String getId();
    String getNome();
    String getDescrizione();
    StatusElement getStato();
    void setId(String id);
    void setNome(String nome);
    void setDescrizione(String descrizione);
    void changeStatus(StatusElement statusElement);
}
