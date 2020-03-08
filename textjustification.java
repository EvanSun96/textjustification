class Solution {
    public List<String> fullJustify(String[] words, int L) {
        List<String> res = new ArrayList<>();

        int index = 0;//index pointer points to the first word of current line
        while (index < words.length) {
            int count = words[index].length();  //the length of current word

            //we will construct the results line by line
            int last = index + 1;//last变量控制当前行的后边界
            while (last < words.length) {
                if (words[last].length() + count + 1 > L) break;// add this one more word, if longer than maxLength, break
                count += 1 + words[last].length();// count will add up this word with one blank space
                last++;//last pointer repoint to the next word
            }//this while loop with continues going until last is the last word OR we have the string longer than maxLength
            StringBuilder builder = new StringBuilder();//let's start to construct current line
            builder.append(words[index]);//add the first word of current line
            int diff = last - index - 1;//diff variable means the number of words in this line
            if (last == words.length || diff == 0) {//if last pointer reach the end OR we have 0 word left
                for (int i = index + 1; i < last; i++) {//let append the word from index to last, one by one
                    builder.append(words[i]);
                }
                for (int i = builder.length(); i < L; i++) {// and we will fill the stringBuilder with blank space
                    builder.append(" ");
                }
            } else {//now, let's see the general case, the instruction says the space between those word should be distributed as evenly as possible
                int spaces = (L - count) / diff;//so the average number of space between each word will be this variable spaces
                int r = (L - count) % diff;//and we calculate the remainder, and try to evenly distributed them
                for (int i = index + 1; i < last; i++) { // from index + 1 to last - 1
                    for (int k = spaces; k > 0; k--) {//first, we add the number of spaces(which is evenly) between current word and next word
                        builder.append(" ");
                    }
                    if (r > 0) {//now let's distributed the remainder number of space
                        builder.append(" ");//each of them only gives one
                        r--;
                    }
                    builder.append(" ");//and because when we calculate count, we have take one blank space into consider so we have to add it in here
                    builder.append(words[i]); //and we append our word
                }
            }
            res.add(builder.toString());//add current line into results
            index = last;//update index pointer to where last pointer indicates
        }
        return res;
    }
}
//time and space complexity will be both O(n)
//because we tranversed every word in words
//and the extra space are StringBuilder and res
