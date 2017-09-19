import java.util.*;
import java.lang.*;
import java.io.*;

class common
{
	public static void main (String[] args)
	{
		String str1, str2;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter first string: ");
		str1 = input.nextLine();
		System.out.println("Enter second string: ");
		str2 = input.nextLine();
		ArrayList<String> ans = new ArrayList<String>();
		int count=0;
		boolean flag1;
		for(int i=0; i<str1.length(); i++) {
			for(int j=i; j<str1.length(); j++) {
				flag1=false;
				for(int k=0; k<str2.length(); k++) {
					if(str2.charAt(k)==str1.charAt(i)) {
						int m=k+1;
						for(; m<str2.length()&&i+m-k<=j&&str2.charAt(m)==str1.charAt(i+m-k); m++);
						if(j-i == m-k-1) {
							flag1=true;
							break;
						}
					}
				}
				if(flag1) {
					boolean flag = true;
					for(int q=0; q<ans.size();q++) {
						String cmp = ans.get(q);
						if(cmp.length() == j-i+1) {
							int w=0;
							for(;w<cmp.length();w++) {
								if(cmp.charAt(w)!=str1.charAt(w+i))
									break;
							}
							if(w==cmp.length()) {
								flag = false;
								break;
							}
						}
					}
					if(flag) {
						ans.add(str1.substring(i,j+1));
						count++;
					}
				}
			}
		}
		System.out.println("Output: " + count);
	}
}