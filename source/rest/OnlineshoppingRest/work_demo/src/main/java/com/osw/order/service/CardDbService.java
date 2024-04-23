package com.osw.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osw.order.model.CardDb;
import com.osw.order.repository.CardDbRepo;
@Service
public class CardDbService implements ICardDbService {
	@Autowired
	private CardDbRepo cr;
	@Override
	public void insertCard(CardDb c) {
		// TODO Auto-generated method stub
		cr.save(c);

	}

	@Override
	public void updateCard(CardDb c) {
		// TODO Auto-generated method stub
		cr.save(c);
	}

	@Override
	public void deleteCard(int cardId) {
		// TODO Auto-generated method stub
		cr.deleteById(cardId);
	}

	@Override
	public CardDb printCard(int cardId) {
		// TODO Auto-generated method stub
		return cr.findById(cardId).get();
	}

	@Override
	public List<CardDb> printAllCards() {
		// TODO Auto-generated method stub
		return cr.findAll();
	}

	@Override
	public boolean validCard(String name, String number, String cvv, String month, String type) {
		// TODO Auto-generated method stub
		List<CardDb> cardlist=cr.findByCardNumber(number);
		for(CardDb card:cardlist) {
		if(card!=null && card.getCardHolderName().equals(name) && card.getCardCVV().equals(cvv) && card.getExpiryMonth().equals(month) && card.getCardType().equals(type)) {
			return true;
		}
		}
		return false;
	}

}
