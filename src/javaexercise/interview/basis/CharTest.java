package javaexercise.interview.basis;

import java.io.UnsupportedEncodingException;

public class CharTest
{
    public static void main(String[] args)
    {
        String str = "中";
        char c = '中';
        byte[] bytes = null;
        byte[] bytes1 = null;

        try
        {
            bytes = str.getBytes("GBK");
            bytes1 = charToByte(c);
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        System.out.println("bytes 大小: " + bytes.length);
        System.out.println("bytes1 大小: " + bytes1.length);
    }

    public static byte[] charToByte(char c)
    {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }
}
