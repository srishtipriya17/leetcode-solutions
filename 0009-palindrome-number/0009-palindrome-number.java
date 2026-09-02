class Solution {
    public boolean isPalindrome(int x) {
        if(x<0)
        return false;

        int original = x;
        long r=0;

        while(x!=0){
            r=r*10+x%10;
            x/=10;
        }

        return original==r;
    }
}