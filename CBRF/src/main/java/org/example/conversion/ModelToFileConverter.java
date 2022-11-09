package org.example.conversion;

import org.example.repository.CurrencyExchangeRepository;

import java.io.File;

public interface ModelToFileConverter {
    public void convert(CurrencyExchangeRepository repo, File fileName);
}
