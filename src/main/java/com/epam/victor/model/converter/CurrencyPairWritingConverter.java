package com.epam.victor.model.converter;

import com.epam.victor.model.currency.CurrencyPair;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class CurrencyPairWritingConverter implements Converter<CurrencyPair, String> {
    @Override
    public String convert(CurrencyPair pair) {
        return pair.toString();
    }
}
