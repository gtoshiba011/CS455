public class Solution {
    public static void main(String[] args) {
        String input = "aaaaabbbb2222222222222233333333333333abcd";

        //System.out.println("Original String Length: " + input.length());
        Solution RLE = new Solution();
        System.out.println("Original String: " + input);
        String encodedStr = RLE.encode(input);
        System.out.println("Encoded String: " + encodedStr);
        //System.out.println("Encoded String Length: " + encodedStr.length());


        String decodedStr = RLE.decode(encodedStr);
        System.out.println("Decoded String: " + decodedStr);
    }
    public String encode(String sourceStr) {
        if(sourceStr == null || sourceStr.length() <= 1) {
            return sourceStr;
        }

        int len = sourceStr.length();
        StringBuilder resultBuilder = new StringBuilder();
        for(int i = 0; i < len; i++) {
            char cur = sourceStr.charAt(i);
            int runLength = 1;
            while((i+1) < len && sourceStr.charAt(i+1) == cur) {
                i++;
                runLength++;
            }

            if(runLength > 1) {
                resultBuilder.append(runLength + "*" + cur);
            }
            else {
                resultBuilder.append(cur);
            }
        }

        return resultBuilder.toString();
    }

    /**
     * 解码Run-Length编码的字符串
     * @param encodedStr
     * @return
     */
    public String decode(String encodedStr) {
        if(encodedStr == null || encodedStr.length() <= 1) {
            return encodedStr;
        }

        int len = encodedStr.length();
        StringBuilder resultBuilder = new StringBuilder();
        for(int i = 0; i < len; i++) {
            int num = 0;
            while(Character.isDigit(encodedStr.charAt(i))) {
                num = num * 10 + encodedStr.charAt(i) - '0';
                i++;
            }
            i++;
            char nextChar = encodedStr.charAt(i);
            for(int j = 0; j < num; j++) {
                resultBuilder.append(nextChar);
            }
        }

        return resultBuilder.toString();
    }

}
