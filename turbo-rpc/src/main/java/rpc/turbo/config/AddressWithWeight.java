package rpc.turbo.config;

import rpc.turbo.util.UnsafeStringUtils;

import java.nio.charset.StandardCharsets;

public class AddressWithWeight {
    public final HostPort address;
    public final int weight;

    public AddressWithWeight(HostPort address, int weight) {
        this.address = address;
        this.weight = weight;
    }

    public AddressWithWeight(byte[] bytes) {
        String str = new String(bytes, StandardCharsets.UTF_8);
        String[] array = str.split("@");

        this.address = new HostPort(array[0]);
        this.weight = Integer.parseInt(array[1]);
    }

    public byte[] toBytes() {
        return UnsafeStringUtils.getUTF8Bytes(toString());
    }

    @Override
    public String toString() {
        return address + "@" + weight;
    }

}
