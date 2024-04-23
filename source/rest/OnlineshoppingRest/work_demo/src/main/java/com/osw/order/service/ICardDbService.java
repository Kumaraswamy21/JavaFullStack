package com.osw.order.service;

import java.time.Month;
import java.util.List;

import com.osw.order.model.CardDb;
import com.osw.order.model.Order;

public interface ICardDbService {
	public void insertCard(CardDb c);
	public void updateCard(CardDb c);
	public void deleteCard(int cardId);
	public CardDb printCard(int cardId);
	public List<CardDb> printAllCards();
	public boolean validCard(String name,String number,String cvv,String month,String type);
}
