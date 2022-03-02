
public class Card
{
	private final String _face;
	private final String _suit;
	private final int _value;/* numeric value in card games */

	public Card(String face, String suit, int value)
	{
		this._face = face;
		this._suit = suit;
		this._value = value;
	}

	/* returns numeric value */
	public int getValue()
	{
		return this._value;
	}

	/* returns type of card */
	public String toString()
	{
		return this._face + " of " + this._suit;

	}
}
