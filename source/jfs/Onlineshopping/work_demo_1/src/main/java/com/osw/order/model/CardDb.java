package com.osw.order.model;

import java.time.Month;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
@Entity
public class CardDb {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int cardId;
	@NotBlank
	private String cardType;
	@NotBlank
	@Size(min=12,max=12,message="CardNumber Should be 12 Digits")
	private String cardNumber;
	@NotBlank
	private String cardHolderName;
	@NotBlank
	private String expiryMonth;
	@NotBlank
	private String cardCVV;
	
	
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public String getCardCVV() {
		return cardCVV;
	}
	public void setCardCVV(String cardCVV) {
		this.cardCVV = cardCVV;
	}
	@Override
	public String toString() {
		return "CardDb [cardId=" + cardId + ", cardType=" + cardType + ", cardNumber=" + cardNumber
				+ ", cardHolderName=" + cardHolderName + ", expiryMonth=" + expiryMonth + ", cardCVV=" + cardCVV + "]";
	}
	
	
}