

/**
 * FileName: TttState2.java
 * @Description:
 * 
 * @author Xunhu(Tiger) Sun
 *         email: sunx2013@my.fit.edu
 * @date Jun 6, 2014 2:12:37 PM
 */
public class ttts2 {
    private final long[] board;
    long a = 0b111L;
    public ttts2() {
        // For convenient. Actually only use board[1] and board[2].
        this.board = new long[3];
    }

    public int get (int l, int r, int c) {
        final int index = getBitIndex(l, r, c);
        System.out.println(index);
        final long mask = 1l << index;
        if ((board[1] & mask) != 0) {
            return 1;
        } else if ((board[2] & mask) != 0) {
            return 2;
        } else {
            return 0;
        }
    }

    private static int getBitIndex (int l, int r, int c) {
        return l * 16 + r * 4 + c;
    }
    private static void print(long ret){
        System.out.println(String.format("0x%016XL,", ret));
    }
    public void endTest () {
        System.out.println("// Straight");
        // Horizontal.
        System.out.println("// column changes, other remain");
        for (int l = 0; l < 4; l++) {
            for (int r = 0; r < 4; r++) {
                long ret = 0;
                for (int c = 0; c < 4; c++) {
                    final int index = getBitIndex(l, r, c);
                    final long mask = 1l << index;
                    ret |= mask;
                }
                assert (Long.bitCount(ret) == 4);
                print(ret);
            }
        }
        // Verticial.
        System.out.println("// row changes, other remain");
        for (int l = 0; l < 4; l++) {
            for (int c = 0; c < 4; c++) {
                long ret = 0;
                for (int r = 0; r < 4; r++) {
                    final int index = getBitIndex(l, r, c);
                    final long mask = 1l << index;
                    ret |= mask;
                }
                assert (Long.bitCount(ret) == 4);
                print(ret);
            }
        }
        // Deep.
        
        System.out.println("// level changes, other remain");
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                long ret = 0;
                for (int l = 0; l < 4; l++) {
                    final int index = getBitIndex(l, r, c);
                    final long mask = 1l << index;
                    ret |= mask;
                }
                assert (Long.bitCount(ret) == 4);
                print(ret);
         }
        }
        System.out.println("// Diagonal");
        // Diagonal 1.
        System.out.println("// level remains, others change");
        for (int l = 0; l < 4; l++) {
            long ret = 0;
            for (int r = 0, c = 0; r < 4 && c < 4; r++, c++) {
                final int index = getBitIndex(l, r, c);
                final long mask = 1l << index;
                ret |= mask;
            }
            assert (Long.bitCount(ret) == 4);
            print(ret);

        }
        for (int l = 0; l < 4; l++) {
            long ret = 0;
            for (int r = 0, c = 3; r < 4 && c >= 0; r++, c--) {
                final int index = getBitIndex(l, r, c);
                final long mask = 1l << index;
                ret |= mask;
            }
            assert (Long.bitCount(ret) == 4);
            print(ret);
      }
        // Diagonal 2.
        System.out.println("// row remains, others change");
        for (int r = 0; r < 4; r++) {
            long ret = 0;
            for (int l = 0, c = 0; l < 4 && c < 4; l++, c++) {
                final int index = getBitIndex(l, r, c);
                final long mask = 1l << index;
                ret |= mask;
            }
            assert (Long.bitCount(ret) == 4);
            print(ret);
        }
        for (int r = 0; r < 4; r++) {
            long ret = 0;
            for (int l = 0, c = 3; l < 4 && c >= 0; l++, c--) {
                final int index = getBitIndex(l, r, c);
                final long mask = 1l << index;
                ret |= mask;
            }
            assert (Long.bitCount(ret) == 4);
            print(ret);
  }
        // Diagonal 3.
System.out.println("// column remains, others change");
        for (int c = 0; c < 4; c++) {
            long ret = 0;
            for (int l = 0, r = 0; l < 4 && r < 4; l++, r++) {
                final int index = getBitIndex(l, r, c);
                final long mask = 1l << index;
                ret |= mask;
            }
            assert (Long.bitCount(ret) == 4);
            print(ret);
        }
        for (int c = 0; c < 4; c++) {
            long ret = 0;
            for (int l = 0, r = 3; l < 4 && r >= 0; l++, r--) {
                final int index = getBitIndex(l, r, c);
                final long mask = 1l << index;
                ret |= mask;
            }
            assert (Long.bitCount(ret) == 4);
            print(ret);
       }
        // Diagonal 4.
        System.out.println("// All change");
        long ret = 0;
        for (int l = 0, r = 0, c = 0; l < 4 && r < 4 && c < 4; l++, r++, c++) {
            final int index = getBitIndex(l, r, c);
            final long mask = 1l << index;
            ret |= mask;
        }
        assert (Long.bitCount(ret) == 4);
        print(ret);
        ret = 0;
        for (int l = 3, r = 0, c = 0; l >= 0 && r < 4 && c < 4; l--, r++, c++) {
            final int index = getBitIndex(l, r, c);
            final long mask = 1l << index;
            ret |= mask;
        }
        assert (Long.bitCount(ret) == 4);
        print(ret);
        ret = 0;
        for (int l = 0, r = 0, c = 3; l < 4 && r < 4 && c >= 0; l++, r++, c--) {
            final int index = getBitIndex(l, r, c);
            final long mask = 1l << index;
            ret |= mask;
        }
        assert (Long.bitCount(ret) == 4);
        print(ret);
        ret = 0;
        for (int l = 3, r = 3, c = 0; l >= 0 && r >= 0 && c < 4; l--, r--, c++) {
            final int index = getBitIndex(l, r, c);
            final long mask = 1l << index;
            ret |= mask;
        }
        assert (Long.bitCount(ret) == 4);
        print(ret);

    }
    private static class Position{
        int l,r,c;
        public Position(int l1, int r1, int c1){
            l = l1;
            r = r1;
            c = c1;
        }
    }
    private static Position getPosition (int index){
        final int l = index / 16;
        index %= 16;
        final int r = index / 4;
        index %= 4;
        final int c = index;
        return new Position(l, r, c);
    }
    public static void main (String[] args) {
        Position p = getPosition(0);
        System.out.println(getBitIndex(p.l,p.r,p.c));
        for (int i =0; i < 64; i++){
            p = getPosition(i);
            System.out.println(getBitIndex(p.l,p.r,p.c));
        }

/*        Main s = new Main();
        s.get(0, 0, 0);
        s.get(1, 0, 0);
        s.get(1, 0, 1);
        s.get(2, 2, 2);
        s.get(3, 3, 3);*/
/*        long a = 0xffffffffffffffffL;
        System.out.println(a);
        new Main().endTest();
        for (long l : winRoutes) {
            print(l);
        }*/
    }
    private static long[] winRoutes = {
        // Straight
     // column changes, other remain
        0x000000000000000FL,
        0x00000000000000F0L,
        0x0000000000000F00L,
        0x000000000000F000L,
        0x00000000000F0000L,
        0x0000000000F00000L,
        0x000000000F000000L,
        0x00000000F0000000L,
        0x0000000F00000000L,
        0x000000F000000000L,
        0x00000F0000000000L,
        0x0000F00000000000L,
        0x000F000000000000L,
        0x00F0000000000000L,
        0x0F00000000000000L,
        0xF000000000000000L,
        // row changes, other remain
        0x0000000000001111L,
        0x0000000000002222L,
        0x0000000000004444L,
        0x0000000000008888L,
        0x0000000011110000L,
        0x0000000022220000L,
        0x0000000044440000L,
        0x0000000088880000L,
        0x0000111100000000L,
        0x0000222200000000L,
        0x0000444400000000L,
        0x0000888800000000L,
        0x1111000000000000L,
        0x2222000000000000L,
        0x4444000000000000L,
        0x8888000000000000L,
        // level changes, other remain
        0x0001000100010001L,
        0x0002000200020002L,
        0x0004000400040004L,
        0x0008000800080008L,
        0x0010001000100010L,
        0x0020002000200020L,
        0x0040004000400040L,
        0x0080008000800080L,
        0x0100010001000100L,
        0x0200020002000200L,
        0x0400040004000400L,
        0x0800080008000800L,
        0x1000100010001000L,
        0x2000200020002000L,
        0x4000400040004000L,
        0x8000800080008000L,
        // Diagonal
        // level remains, others change
        0x0000000000008421L,
        0x0000000084210000L,
        0x0000842100000000L,
        0x8421000000000000L,
        0x0000000000001248L,
        0x0000000012480000L,
        0x0000124800000000L,
        0x1248000000000000L,
        // row remains, others change
        0x0008000400020001L,
        0x0080004000200010L,
        0x0800040002000100L,
        0x8000400020001000L,
        0x0001000200040008L,
        0x0010002000400080L,
        0x0100020004000800L,
        0x1000200040008000L,
        // column remains, others change
        0x1000010000100001L,
        0x2000020000200002L,
        0x4000040000400004L,
        0x8000080000800008L,
        0x0001001001001000L,
        0x0002002002002000L,
        0x0004004004004000L,
        0x0008008008008000L,
        // All change
        0x8000040000200001L,
        0x0001002004008000L,
        0x1000020000400008L,
        0x1000020000400008L,
};
}
