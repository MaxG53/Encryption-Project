class Main {
  public static void main(String[] args) {
    (new Main()).init();
  }
  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}

  void init() {
    // Original Text
    String originalText = Input.readFile("Original.txt");

    // Encoding
    String encodedMsg1 = rotate90(originalText);
    Input.writeFile("Encode1.txt", encodedMsg1);

    String encodedMsg2 = reverseShiftCipher(encodedMsg1);
    Input.writeFile("Encode2.txt", encodedMsg2);

    String encodedMsg3 = skipByFiveCipher(encodedMsg2);
    Input.writeFile("Encode3.txt", encodedMsg3);

    String encodedMsg4 = AddFillerCharacter(encodedMsg3);
    Input.writeFile("Encode4.txt", encodedMsg4);

    String encodedMsg5 = CutByThirds(encodedMsg4);
    Input.writeFile("Encode5.txt", encodedMsg5);

    String encodedMsg6 = swapEveryTwo(encodedMsg5);
    Input.writeFile("Encode6.txt", encodedMsg6);

    // Decoding
    String decodedMsg6 = reverseSwapEveryTwo(encodedMsg6);
	Input.writeFile("Decode6.txt", decodedMsg6);
	String decodedMsg5 = decodeCutByThirds(decodedMsg6);
	Input.writeFile("Decode5.txt", decodedMsg5);
	String decodedMsg4 = decodeAddFillerCharacter(decodedMsg5);
	Input.writeFile("Decode4.txt", decodedMsg4);
	String decodedMsg3 = decodeSkipByFiveCipher(decodedMsg4);
	Input.writeFile("Decode3.txt", decodedMsg3);
	String decodedMsg2 = decodeReverseShiftCipher(decodedMsg3);
	Input.writeFile("Decode2.txt", decodedMsg2);
	String decodedMsg1 = decodeRotate90(decodedMsg2);
	Input.writeFile("Decode1.txt", decodedMsg1);

}

  
  // Cipher by a rotation of 90 degrees
  String rotate90(String text) {
    char[][] tbl = new char[4][4];
    int index = 0;
    for (int x = 0; x < 4; x++) {
      for (int i = 0; i < 4; i++) {
        if (index < text.length()) {
          tbl[x][i] = text.charAt(index++);
        } else {
          tbl[x][i] = 'b'; 
        }
      }
    }
	
    String bld = "";
    for (int col = 0; col < 4; col++) {
      for (int row = 3; row >= 0; row--) {
        bld += tbl[row][col];
      }
      bld += "b/"; 
    }
    return bld;
  }
  
  // Decoding: Cipher by a rotation of 90 degrees
    String decodeRotate90(String encodedText) {
    String[] columns = encodedText.split("b/");
    char[][] tbl = new char[4][4];
    for (int col = 0; col < columns.length; col++) {
        String columnText = columns[col];
        int index = 0;
        for (int row = 3; row >= 0; row--) {
            if (index < columnText.length()) {
                tbl[row][col] = columnText.charAt(index);
                index++;
            } else {
                tbl[row][col] = 'b';
            }
        }
    }
    String result = "";
    for (int row = 0; row < 4; row++) {
        for (int col = 0; col < 4; col++) {
            if (tbl[row][col] != 'b') {
                result += tbl[row][col];
            }
        }
    }
    return result;
}

  // Cipher a shift by reversing the alphabet by a variable that counts down and resets by 3 before keying
  String reverseShiftCipher(String text) {
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String reversed = "";
    for (int x = alphabet.length() - 1; x >= 0; x--) {
      reversed += alphabet.charAt(x);
    }
	int shift = 3;
    String result = "";

    for (int x = 0; x < text.length(); x++) {
      char ch = text.charAt(x);
      if (Character.isLetter(ch)) {
        int index = reversed.indexOf(Character.toUpperCase(ch));
        int shiftedIndex = (index + shift) % 26;
        result += reversed.charAt(shiftedIndex);
        shift--;
        if (shift < 0) shift = 3;
      } else{
        result += ch;
      }
    }
    return result;
  }
  
  // Decode: Cipher a shift by reversing the alphabet by a variable that counts down and resets by 3 before keying
    String decodeReverseShiftCipher(String text) {
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String reversed = "";
    for (int x = alphabet.length() - 1; x >= 0; x--) {
        reversed += alphabet.charAt(x); 
    }
    String result = "";
    int shift = 3;
    for (int x = 0; x < text.length(); x++) {
        char ch = text.charAt(x);
        if (Character.isLetter(ch)) {
            int index = reversed.indexOf(Character.toUpperCase(ch));
            int shiftedIndex = (index - shift + 26) % 26;
            result += reversed.charAt(shiftedIndex);
            shift--;
            if (shift < 0) shift = 3;
        } else {
            result += ch;
        }
    }
    return result;
}
  // Cipher implementation that skips every 5th character
  String skipByFiveCipher(String text) {
    String result = "";
    for (int x = 0; x < text.length(); x++) {
      int newIndex = (x * 5) % text.length();
      result += text.charAt(newIndex);
    }
    return result;
  }
  
   // Decoding Skip By 5 Cipher
   String decodeSkipByFiveCipher(String text) {
		char[] result = new char[text.length()];
    for (int x = 0; x < text.length(); x++) {
        int originalIndex = (x * 5) % text.length();
        result[originalIndex] = text.charAt(x);
    }
    return new String(result);
}



  String decode(String txt){
    String bld="";
    int ascii;
    char ch='\0';
    for(int x=0; x<=txt.length()-1;x++){
      ch=txt.charAt(x);
      ascii=(int)ch;
      ascii-=1;
      bld+= (char)ascii;
    }
    return bld;
  }
  
  int indexOf(char ch, char[] arry){
    for(int x=0; x<=arry.length-1; x++){
      if(arry[x]==ch){
        return x;
      }
    }
    return -1;
  }
  int randInt(int lower, int upper){
    int range = upper - lower;
    return (int)(Math.random()*range+lower);
  }
  
  
  //Cut the character by third - Yunzhao
    String CutByThirds(String txt) {
        int length = txt.length();
        int third = length / 3;
        String p1 = txt.substring(0, third);
        String p2 = txt.substring(third, Math.min(2 * third, length));
        String p3 = txt.substring(2 * third);
        return p1 + "/" + p2 + "/" + p3;
    }
	
	//Decode: Cut by Thirds:
	String decodeCutByThirds(String text) {
    String[] parts = text.split("/");
    String part1 = "";
    String part2 = "";
    String part3 = "";
    if (parts.length > 0) part1 = parts[0];
    if (parts.length > 1) part2 = parts[1];
    if (parts.length > 2) part3 = parts[2];
    char[] result = new char[part1.length() + part2.length() + part3.length()];
    int index = 0;
    for (int i = 0; i < result.length; i++) {
        if (i % 3 == 0) {
            if (index < part1.length()) {
                result[i] = part1.charAt(index);
            }
        } else if (i % 3 == 1) {
            if (index < part2.length()) {
                result[i] = part2.charAt(index);
            }
        } else if (i % 3 == 2) {
            if (index < part3.length()) {
                result[i] = part3.charAt(index);
                index++;
            }
        }
    }
    return new String(result);
}
	//Swap every two characters
    String swapEveryTwo(String txt) {
        String swapped = "";
		char ch1, ch2;
		
		for(int x = 0; x < txt.length() - 1; x += 2){
			ch1 = txt.charAt(x);
			ch2 = txt.charAt(x + 1);
			swapped += ch2;
			swapped += ch1;
		}
		if(txt.length() % 2 != 0){
			swapped += txt.charAt(txt.length() - 1);
		}
		return swapped;
    }
	
	// Decode: Swap every 2
	String reverseSwapEveryTwo(String txt) {
    String reversed = ""; 
    char ch1, ch2; 
	for (int x = 0; x < txt.length() - 1; x += 2) { 
		ch1 = txt.charAt(x + 1);  
		ch2 = txt.charAt(x); 
		
		reversed += ch1; reversed += ch2; 
    }   
   if(txt.length() % 2 != 0) { 
   reversed += txt.charAt(txt.length() - 1); 
    } 
   return reversed;
 } 

	//Padding 
	String AddFillerCharacter(String txt){
       while (txt.length() % 5 != 0) {
            txt += 'X';
        }
        return txt;
   }
   //Decode: Padding
   String decodeAddFillerCharacter(String text) {
    String result = "";
    for (int i = 0; i < text.length(); i++) {
        if (text.charAt(i) != 'X') { 
            result += text.charAt(i);
        }
    }
    return result;
	}
}



 

  
  
  
  
  



  
  

  
  


  
  
  
  
  
  
  
  
  
  
  
  
  
  
  




  
  




