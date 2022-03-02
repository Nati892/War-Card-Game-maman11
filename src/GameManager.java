import javax.swing.JOptionPane;

public class GameManager
{
	boolean stopGame = false;
	static DeckOfCards Player1Deck;
	static DeckOfCards Player2Deck;
	static final int NUM_OF_WAR_TURNS = 3;

	/* starting point, calling game loop, where the game is managed */
	public static void main(String[] args)
	{

		JOptionPane.showMessageDialog(null, "Hi There! welcome to the war card game!"
				+ "At any point if you want to exit then hit the cancel button");/* Hello message */
		GameLoop();
		JOptionPane.showMessageDialog(null, "Good Bye, I hope you enjoyed the game");
	}

	public static void GameLoop()
	{
		String result;
		boolean anotherGame = true;
		while (anotherGame)
		{
			result = PlayGame();
			String text = result + "\n" + "Would you like to play again?";
			int playerChoice = JOptionPane.showConfirmDialog(null, text, null, JOptionPane.OK_CANCEL_OPTION

			);
			/**/
			if (playerChoice == JOptionPane.CANCEL_OPTION)
				anotherGame = !anotherGame;

		}

	}

	public static String PlayGame()
	{
		String result = null;

		myDialogBox("A new game has started, Have fun!");/* game start message */
		divideDecks();

		while (Player1Deck.size() > 0 && Player2Deck.size() > 0)
		{
			playTurn();
		}
		if (Player1Deck.size() == 0 && Player2Deck.size() == 0)
		{

			result = "its a Draw! both players are winners!";
		}
		if (Player1Deck.size() == 0)
		{

			result = "first player has won";
		}
		if (Player2Deck.size() == 0)
		{
			/* player 2 wins */
			result = "second player has won";
		}
		return result;
	}

	/* divides a new deck between both decks */
	public static void divideDecks()
	{
		// initialize decks if needed
		// and clear them if not
		if (Player1Deck == null)
			Player1Deck = new DeckOfCards(DeckOfCards.type_of_deck.EMPTY);
		else
			Player1Deck.clearDeck();

		if (Player2Deck == null)
			Player2Deck = new DeckOfCards(DeckOfCards.type_of_deck.EMPTY);
		else
			Player2Deck.clearDeck();

		DeckOfCards newDeck = new DeckOfCards(DeckOfCards.type_of_deck.FULL);
		newDeck.shuffle();
		int Decksize = newDeck.size();
		for (int i = 0; i < Decksize; i++)/* split cards between decks */
		{
			if (i % 2 == 0)
			{
				Player1Deck.addCardAtTop(newDeck.removeCardFromTop());
			} else
			{
				Player2Deck.addCardAtTop(newDeck.removeCardFromTop());
			}

		}

	}

	public static void playTurn()/* play turn */
	{

		if (Player1Deck.getCardFromTop().getValue() > Player2Deck.getCardFromTop().getValue())
		{
			myDialogBox(
					"WINNER: first player!" + "\n" + "first player's card: " + Player1Deck.getCardFromTop().toString()
							+ "\nsecond player's card: " + Player2Deck.getCardFromTop().toString());

			moveCards(Player1Deck, Player2Deck);
		} else
		{
			if (Player1Deck.getCardFromTop().getValue() < Player2Deck.getCardFromTop().getValue())
			{

				myDialogBox("WINNER: second player!" + "\n" + "first player's card: "
						+ Player1Deck.getCardFromTop().toString() + "\nsecond player's card: "
						+ Player2Deck.getCardFromTop().toString());

				moveCards(Player2Deck, Player1Deck);
			} else/* if there is a tie then start a war stage */
			{

				DeckOfCards tempDeck = new DeckOfCards(DeckOfCards.type_of_deck.EMPTY);
				warStage(tempDeck);
				tempDeck.clearDeck();
			}
		}

	}

	public static void warStage(DeckOfCards tempDeck)
	{
		myDialogBox("its a Tie!\n" + "first player's card: " + Player1Deck.getCardFromTop().toString() + "\n "
				+ "second player's card: " + Player2Deck.getCardFromTop().toString());

		for (int i = 0; i < NUM_OF_WAR_TURNS && Player1Deck.size() > 0
				&& Player2Deck.size() > 0; i++)/* while both players have cards in their decks */
		{
			myDialogBox("first player's card:" + Player1Deck.getCardFromTop() + "\n" + "second player's card:"
					+ Player2Deck.getCardFromTop());

			tempDeck.addCardAtBottom(Player1Deck.removeCardFromTop());
			tempDeck.addCardAtBottom(Player2Deck.removeCardFromTop());
		}
		if (Player1Deck.size() > 0 && Player2Deck.size() > 0)/* in case both still have cards */
		{

			if (Player1Deck.getCardFromTop().getValue() > Player2Deck.getCardFromTop().getValue())/* if player 1 wins */
			{
				tempDeck.addCardAtBottom(Player1Deck
						.removeCardFromTop());/* dump both top cards to the tempDeck and add that deck to the winner */
				tempDeck.addCardAtBottom(Player2Deck.removeCardFromTop());
				Player1Deck.add_Deck(tempDeck);
			} else
			{
				if (Player1Deck.getCardFromTop().getValue() < Player2Deck.getCardFromTop()
						.getValue())/* if player 2 wins */
				{
					tempDeck.addCardAtBottom(Player1Deck.removeCardFromTop());/*
																				 * dump both top cards to the tempDeck
																				 * and add that deck to the winner
																				 */
					tempDeck.addCardAtBottom(Player2Deck.removeCardFromTop());
					Player2Deck.add_Deck(tempDeck);

				} else/*
						 * in case of a tie then the equality function is called again with the same
						 * stack of cards
						 */
				{
					tempDeck.addCardAtBottom(Player1Deck.removeCardFromTop());
					tempDeck.addCardAtBottom(Player2Deck.removeCardFromTop());
					warStage(tempDeck);
				}
			}
		}
	}

	/* Displays exit message and exits the game */
	public static void endGameInTheMiddle()
	{

		JOptionPane.showMessageDialog(null, "Good Bye, I hope you enjoyed playing the game!");
		System.exit(0);
		;
	}

	/* my custom dialog box that exits the program if the player clicks "cancel */
	public static void myDialogBox(String showText)
	{
		if (showText != null)
		{
			int result = JOptionPane.showConfirmDialog(null, showText, null, JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.CANCEL_OPTION)
				endGameInTheMiddle();
		}

	}

	/* move top cards from top of both given decks:D1,D2 to bottom of D1 */
	public static void moveCards(DeckOfCards D1, DeckOfCards D2)
	{

		D1.addCardAtBottom(D1.removeCardFromTop());
		D1.addCardAtBottom(D2.removeCardFromTop());
	}

}
