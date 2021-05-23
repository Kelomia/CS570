The Binary.java 's code has mistake on:
method add(BinaryNumber a): which return wrong value;
method getDigit(int index): wrong check for index;
BinaryNumber(int length): wrong check for negative and zero length is missing;
BinaryNumber(String str): wrong check for empty string and non"0" and "1" characters is missing.

Fixed:
_check the length negative or zero;

_check the str empty or not, Yes---set it as "0000". And ignore the non-"1"and non-"0" part.
(For example, str: 31020 will be Binary Number:01000).

_index now checked negative, and reset the condition with BinaryNumer.length .

_Rewrite the method add(BinaryNumber a), since the old version was misunderstanding the requirement.
Now it will add the BinaryNumber a.data to this.data, when they are equal-length. Else, do nothing.
And when overflow, mark it by this.overflow.