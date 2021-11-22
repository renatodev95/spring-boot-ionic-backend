package com.renatodev.cursomc.services;

import com.renatodev.cursomc.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
        Calendar c = Calendar.getInstance();
        c.setTime(instanteDoPedido);
        c.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(c.getTime());
    }
}
