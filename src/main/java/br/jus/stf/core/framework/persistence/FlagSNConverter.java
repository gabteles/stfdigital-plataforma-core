package br.jus.stf.core.framework.persistence;

import javax.persistence.AttributeConverter;

/**
 * Faz a conversão de um boleano para a flag S ou N
 * 
 * @author lucas.rodrigues
 *
 */
public class FlagSNConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		return Boolean.TRUE.equals(attribute) ? "S" : "N";
	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		return "S".equals(dbData);
	}

}
