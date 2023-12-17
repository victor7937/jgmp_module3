package com.epam.victor.model.converter;

import com.epam.victor.model.currency.CurrencyPair;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class CurrencyPairReadingConverter implements Converter<String, CurrencyPair> {

    @Override
    public CurrencyPair convert(String pairStr) {
        return CurrencyPair.of(pairStr);
    }
}
