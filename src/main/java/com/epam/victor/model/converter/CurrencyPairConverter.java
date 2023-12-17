package com.epam.victor.model.converter;

import com.epam.victor.model.currency.CurrencyPair;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CurrencyPairConverter implements AttributeConverter<CurrencyPair, String> {
    @Override
    public String convertToDatabaseColumn(CurrencyPair currencyPair) {
        return currencyPair.toString();
    }

    @Override
    public CurrencyPair convertToEntityAttribute(String s) {
        return CurrencyPair.of(s);
    }
}
