package rpc.turbo.serialization.kryo.test;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class HelloKryo {
    public static void main(String[] args) throws FileNotFoundException {
        Kryo kryo = new Kryo();

        // 这里需要依次注册类
        kryo.register(Hello.class);
        kryo.register(String[].class);
        kryo.register(HashMap.class);

        // 写入各种数据
        Hello hello = new Hello();
        hello.a = 1;
        hello.b = 100000000000000000L;
        hello.c = true;
        hello.value = "Hello Kryo!";
        for (int i=0; i<10; i++) {
            hello.array[i] = "你好" + i;
            hello.map.put(hello.array[i], i);
        }

        // 写入文件
        Output output = new Output(new FileOutputStream("D:\\file.bin"));
        kryo.writeObject(output, hello);
        output.close();

        // 读取文件
        Input input = new Input(new FileInputStream("D:\\file.bin"));
        Hello hello1 = kryo.readObject(input, Hello.class);
        input.close();

        // 测试
        if (hello.value.equals(hello1.value)) {
            System.out.println("hello value equals");
        }
    }

    static class Hello {
        public int a;
        public long b;
        public boolean c;
        public String value;
        public String[] array = new String[10];
        public Map<String, Integer> map = new HashMap<>();
    }

}
