import java.util.regex.*;
class Similary
{
    private static String delstr(String str)
    {
        return str
            .replaceAll("/\\/\\/.*?\\n/","")
            .replaceAll("/\\/\\*.*?\\*\\//","")
            .replaceAll("/\\n+/","\\n")
            .replaceAll("/[\\f\\t\\v\\r ]+/"," ")
            .replaceAll("/\\n+/","\\n");
    }
    private static int lcs(String[] s1,String[] s2)
    {
        int[][] dp=new int[s1.length+1][s2.length+1];
        for(int i=0;i<=s1.length;i++)dp[i][0]=0;
        for(int i=0;i<=s2.length;i++)dp[0][i]=0;

        for(int i=0;i<s1.length;i++)
            for(int j=0;j<s2.length;j++)
                dp[i+1][j+1]=s1[i].equals(s2[j])?dp[i][j]+1:Math.max(dp[i+1][j],dp[i][j+1]);
        return dp[s1.length][s2.length];
    }
    private static String kwzation(String code)
    {
        String kwlist="\\!|\\%|\\&|\\(|\\)|\\*|\\=|\\+|\\,|\\-|\\>|\\#|\\.|\\}|\\{|\\/|\\:|\\;|\\<|\\?|\\[|\\]|\\^|\\||\\~|\\W(reinterpret_cast|synchronized|dynamic_cast|static_cast|instanceof|implements|const_cast|transient|push_back|protected|namespace|make_pair|interface|algorithm|volatile|unsigned|template|register|operator|nonlocal|iterator|iostream|explicit|continue|abstract|wchar_t|virtual|typedef|private|package|mutable|include|finally|extends|default|cstring|cstdlib|boolean|vector|typeid|throws|switch|struct|string|static|sizeof|signed|return|public|native|lambda|insert|inline|import|global|friend|extern|export|except|double|delete|cstdio|cctype|assert|yield|while|using|union|throw|super|stack|short|raise|queue|float|final|false|const|cmath|class|catch|break|False|main|with|void|true|this|sort|pass|null|long|goto|from|enum|else|elif|char|case|byte|bool|auto|True|None|try|set|not|new|map|int|for|del|def|asm|and|or|is|in|if|do|as)\\W|\\n";
        String xcode=code.replaceAll("/\r/","") //去除蛋疼的\r
            .replaceAll("/(\\#include.*?\\<)(.*?)\\.h.*?\\>/","$1c$2>")
            .replaceAll("/((?:\\#|\\}|\\{).*?)\\n/","$1;\\n") //给需要换行的加上;
            .replaceAll("/(\\W)/"," $1 "); //给符号用空格分开,避免匹配不到
        Matcher matcher = Pattern.compile(kwlist).matcher(xcode);
        String res="";
        while (matcher.find())res+=matcher.group(0);
        return res
            .replaceAll("/\\s/","") //去除空字符
            .replaceAll("/\\;/","\\n"); //把;替换为换行符
    }
    public static String[] prepare(String code)
    {
        return kwzation(delstr(code)).split("\n");
    }
    public static double similary(String c1,String c2)
    {
        c1=kwzation(delstr(c1));
        c2=kwzation(delstr(c2));
        if(c1.replaceAll("/\\s/","").equals(c2.replaceAll("/\\s/","")))return 1;
        return similary(c1.split("\n"),c2.split("\n"));
    }
    public static double similary(String[] a1,String[] a2)
    {
        double l=(a1.length+a2.length)/2.0;
        return lcs(a1,a2)/l;
    }
    /*
    //test
    public static void main(String[] args)
    {
        String a="#include <stdio.h>\n"+
        "int main()\n"+
        "{"+
        "return 0;"+
        "}";
        String b="#include <stdio.h>\n"+
        "int main()\n"+
        "{"+
        "return 1112;"+
        "}";
        System.out.println(Similary.similary(a,b));
    }
    */
}
