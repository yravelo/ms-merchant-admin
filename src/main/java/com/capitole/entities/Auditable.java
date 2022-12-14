package com.capitole.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.capitole.model.CapitoleLocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Auditable implements Serializable {

   @NotNull
   @CreatedDate
   @CapitoleLocalDateTime
   protected LocalDateTime createDateTime;

   @LastModifiedDate
   @CapitoleLocalDateTime
   protected LocalDateTime updateDateTime;

}
