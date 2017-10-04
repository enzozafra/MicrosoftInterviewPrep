class BitManipulation {
  public boolean getBit(int num, int i) {
    return ((num & (1 << i)) != 0);
  }

  public int setBit(int num, int i) {
    return num | (1 << i);
  }

  public int clearBit(int num, int i) {
    return num & ~(1 << i);
  }

  public int updateBit(int num, int i, boolean bitIs1) {
    int value = bitIs1 ? 1 : 0;
    int mask = ~(1 << i);
    return (num & mask) | (value << i);
  }
}
