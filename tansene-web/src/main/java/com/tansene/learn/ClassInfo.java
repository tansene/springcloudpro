package com.tansene.learn;

import sun.misc.Unsafe;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;

public class ClassInfo {
    public int a;
    public int b;
    public int c;

    public Object o1;
    public Object o2 = new TestObjectSize();
    public Object o3;

    public int[] int1;
    public int[] int2;
    public int[] int3;

    public char char1;
    public char char2;
    public char char3;

    public static void main(String[] args) throws Exception {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
        //操作系统:windows 64位
        //关闭指针压缩-XX:-UseCompressedOops
        Unsafe unsafeInstance = getUnsafeInstance();
        {
            //普通对象头大小:16Byte.
            //                8:Mark Word + 8:Class Pointer Class对象（其对应的元数据对象）的内存地址
            long testObjectSizeOffset = unsafeInstance.objectFieldOffset(TestObjectSize.class.getDeclaredField("flag")); //16
            //testObjectSizeOffset为字段flag对于对象TestObjectSize的偏向地址,则说明对象头占用了16字节
            int flag = unsafeInstance.getInt(new TestObjectSize(), testObjectSizeOffset);//100
            //字段flag为基础类型int占用4字节,则getInt取到flag的值100
        }
        {
            //数组对象头大小:24Byte:(8+8+4)
            //                8:Mark Word + 8:Class Pointer Class对象（其对应的元数据对象）的内存地址 + 4:数组长度
            int[] ints = new int[200];
            long IntArrayOffset = unsafeInstance.arrayBaseOffset(int[].class);//24 数组对象头的大小
            int intsCount = unsafeInstance.getInt(ints, 16L);//200 ints数组的大小
        }

        //注意在对象内存分布时,基础类型和引用类型(8Byte:为对象的地址)会在内存中分开,如果所有基础类型的size不是8的倍数则对齐填充
        //下面可以展示对象填充(char3从偏移量32到34,o1偏移量为40,40-34=6填充6Byte)
        ClassInfo ClassInfo = new ClassInfo();

        {
            long aOffset = unsafeInstance.objectFieldOffset(ClassInfo.class.getDeclaredField("a"));//16
            long bOffset = unsafeInstance.objectFieldOffset(ClassInfo.class.getDeclaredField("b"));//20
            long cOffset = unsafeInstance.objectFieldOffset(ClassInfo.class.getDeclaredField("c"));//24
            long o1Offset = unsafeInstance.objectFieldOffset(ClassInfo.class.getDeclaredField("o1"));//40
            long o2Offset = unsafeInstance.objectFieldOffset(ClassInfo.class.getDeclaredField("o2"));//48
            long o3Offset = unsafeInstance.objectFieldOffset(ClassInfo.class.getDeclaredField("o3"));//56
            long int1Offset = unsafeInstance.objectFieldOffset(ClassInfo.class.getDeclaredField("int1"));//64
            long int2Offset = unsafeInstance.objectFieldOffset(ClassInfo.class.getDeclaredField("int2"));//72
            long int3Offset = unsafeInstance.objectFieldOffset(ClassInfo.class.getDeclaredField("int3"));//80
            long char1Offset = unsafeInstance.objectFieldOffset(ClassInfo.class.getDeclaredField("char1"));//28
            long char2Offset = unsafeInstance.objectFieldOffset(ClassInfo.class.getDeclaredField("char2"));//30
            long char3Offset = unsafeInstance.objectFieldOffset(ClassInfo.class.getDeclaredField("char3"));//32
        }
        //如何获取ClassInfo中字段o2引用对象(TestObjectSize)的flag字段:
        long o2Offset = unsafeInstance.objectFieldOffset(ClassInfo.class.getDeclaredField("o2"));//48
        //os为对象引用占8字节存放对象地址
        long o2Address = unsafeInstance.getLong(ClassInfo, o2Offset);//此处为ClassInfo中o2对象的地址

        //TestObjectSize为普通对象,则跳过16Byte后的4字节存放int
        int unsafeInstanceInt = unsafeInstance.getInt(o2Address + 16);
        assert unsafeInstanceInt == ((TestObjectSize) ClassInfo.o2).flag;

        System.out.println("ClassInfo地址   :" + getObjectAddress(ClassInfo));
        System.out.println("ClassInfo.o2地址:" + getObjectAddress(ClassInfo.o2));

        System.out.println("ClassInfo地址   :" + getObjectAddress(ClassInfo));
        System.out.println("ClassInfo.o2地址:" + getObjectAddress(ClassInfo.o2));
        System.out.println("ClassInfo地址   mark  init:" + getObjectMarkValue(ClassInfo));

        synchronized (ClassInfo) {
            System.out.println("ClassInfo地址   mark  lock:" + getObjectMarkValue(ClassInfo));
        }
        System.out.println("...");
    }

    public static Unsafe getUnsafeInstance() throws Exception {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }

    public static class TestObjectSize {
        public int flag = 100;
    }

    /**
     * 获取目标对象头mark值
     *
     * @param o 目标对象
     * @return 目标对象地址
     */
    public static Long getObjectMarkValue(Object o) {

        try {
            Unsafe unsafeInstance = getUnsafeInstance();
            return unsafeInstance.getLong(o, 0L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取目标对象头类型对象地址
     *
     * @param o 目标对象
     * @return 类型对象地址
     */
    public static Long getObjectClassValue(Object o) {

        try {
            Unsafe unsafeInstance = getUnsafeInstance();
            return unsafeInstance.getLong(o, 8L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取目标对象的内存地址 10进制
     *
     * @param o 目标对象
     * @return 目标对象地址
     */
    public static Long getObjectAddress(Object o) {

        ObjectAddressHolder objectAddressHolder = new ObjectAddressHolder();
        objectAddressHolder.object = o;
        try {
            Unsafe unsafeInstance = getUnsafeInstance();
            long objectFieldOffset = unsafeInstance.objectFieldOffset(ObjectAddressHolder.class.getDeclaredField("object"));
            long objectAddress = unsafeInstance.getLong(objectAddressHolder, objectFieldOffset);
            objectAddressHolder.object = null;
            objectAddressHolder = null;
            return objectAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class ObjectAddressHolder {
        public Object object;
    }
}


