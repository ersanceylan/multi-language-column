package com.kodgemisi.blog.multilanguage;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created on May, 2019
 *
 * @author ersan
 */
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Getter
@Setter
@Entity
class Product {

	@Id
	@GeneratedValue
	private Long id;

	@Embedded
	@AttributeOverride(name = "texts", column = @Column(name = "name", columnDefinition = "jsonb"))
	private MultiLanguageText name;

	@Embedded
	@AttributeOverride(name = "texts", column = @Column(name = "description", columnDefinition = "jsonb"))
	private MultiLanguageText description;

	private BigDecimal price;

}
