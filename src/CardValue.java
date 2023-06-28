public enum CardValue {
  // holds all the possible values for the card
  // and methods for getting information
  TWO(2, "two"),
  THREE(3, "three"),
  FOUR(4, "four"),
  FIVE(5, "five"),
  SIX(6, "six"),
  SEVEN(7, "seven"),
  EIGHT(8, "eight"),
  NINE(9, "nine"),
  TEN(10, "ten"),
  JACK(11, "jack"),
  QUEEN(12, "queen"),
  KING(13, "king"),
  ACE(14, "ace"),;

  private int ValueInt;
  private String ValueString;

  private CardValue(int ValueInt, String ValueString) {
    this.ValueInt = ValueInt;
    this.ValueString = ValueString;
  }

  public int getCardValue() {
    return ValueInt;
  }

  public String getValueString() {
    return ValueString;
  }
}
