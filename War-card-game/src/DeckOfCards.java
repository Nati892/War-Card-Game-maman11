import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;

public class DeckOfCards
{
	/*
	 * Top of deck is the highest index bottom is index 0. and the strongest card is
	 * ACE, then King , Queen etc.
	 */
	enum type_of_deck
	{
		FULL, EMPTY
	}

	private static final SecureRandom randomNumbers = new SecureRandom();
	private static final int NUMBER_OF_CARDS = 52;
	private static final int BOTTOM_OF_DECK = 0;
	private ArrayList<Card> _deck = new ArrayList<Card>();

	/* Constructor of deck, can be either full or empty */
	public DeckOfCards(type_of_deck mytype)
	{
		if (mytype == type_of_deck.FULL)
		{
			String[] faces = { "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack",
					"Queen", "King", "Ace" };
			String[] suits = { "Hearts", "Diamond", "Clubs", "Spades" };
			// fills deck with all cards
			for (int i = 0; i < NUMBER_OF_CARDS; i++)
			{
				this._deck.add(new Card(faces[i % 13], suits[i / 13], i % 13));
			}
		}

	}

	/* removes all the cards from the deck */
	public void clearDeck()
	{
		this._deck.clear();
	}

	/* adds card at the top of the deck */
	public void addCardAtTop(Card card)
	{
		this._deck.add(card);

	}     
	
	


	/* adds card from the bottom of the deck */
	public void addCardAtBottom(Card card)
	{
		this._deck.add(BOTTOM_OF_DECK, card);

	}

	/*
	 * gets a card at specific index if the card exits, if does not exist then
	 * returns null
	 */
	public Card getCard(int index)
	{
		if (index < this._deck.size())
			return this._deck.get(index);
		else
			return null;

	}

	/* returns a reference to the card at the top */
	public Card dealCard()
	{

		if (_deck.size() > 0)
		{
			return getCard(_deck.size() - 1);

		} else
		{
			return null;

		}

	}

	/* removes the card at the top, if the deck is not empty */
	public Card removeCardFromTop()
	{
		Card temp = getCard(_deck.size() - 1);
		if (_deck.size() > 0)
			_deck.remove(_deck.size() - 1);
		return temp;
	}

	public Card getCardFromTop()
	{
		if (_deck.size() > 0)
			return this.getCard(this._deck.size() - 1);
		else
			return null;
	}

	/* shuffles the deck */
	public void shuffle()
	{
		for (int first = 0; first < _deck.size(); first++)
		{
			int second = randomNumbers.nextInt(_deck.size());// randomizes a card to swap with
			Card temp = _deck.get(first);
			_deck.set(first, _deck.get(second));
			_deck.set(second, temp);
		}

	}

	public void add_Deck(DeckOfCards DeckToMove)
	{
		while (DeckToMove.size() > 0)
		{
			this.addCardAtBottom(DeckToMove.removeCardFromTop());
		}
	}

	/* returns amount of cards in deck */
	public int size()
	{
		return this._deck.size();

	}

	public String toString()
	{
		return this._deck.toString();
	}

}
