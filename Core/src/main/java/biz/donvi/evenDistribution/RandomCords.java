package biz.donvi.evenDistribution;

import java.util.Random;

public class RandomCords {
    private static final Random r = new Random();

    static double randomG(double shrink, double center) {
        if (center < 0.0D || center > 1.0D)
            throw new IllegalArgumentException("Center must be between 0 and 1 inclusive.");
        while (true) {
            double randG = r.nextGaussian() / shrink + center;
            if (randG >= 0.0D && randG < 1.0D)
                return randG;
        }
    }

    public static int[] asIntArray2w(double[] cordPair) {
        return new int[] { (int)cordPair[0], (int)cordPair[1] };
    }

    public static double[] getRandXySquare(int radiusMax, int radiusMin) {
        double[] randPairAtSize = { Math.random() * (radiusMax - radiusMin) + radiusMin, Math.random() * (radiusMax + radiusMin) - radiusMin };
        return TransitionMatrices.multiplyMatrixVector(TransitionMatrices.ROTATIONS_0_90_180_270[
                (int)(Math.random() * 4.0D)], randPairAtSize);
    }

    public static double[] getRandXyRectangle(double rx, double ry) {
        return new double[] { Math.random() * rx * 2.0D - rx,
                Math.random() * ry * 2.0D - ry };
    }

    public static double[] getRandXyRectangle(double rx, double ry, double hrx, double hry, double hcx, double hcy) {
        double xb = hcx - hrx, xt = hcx + hrx;
        double yb = hcy - hry, yt = hcy + hry;
        double s0l = rx - xt, s0h = ry - yb;
        double s1l = xt + rx, s1h = ry - yt;
        double s2l = xb + rx, s2h = yt + ry;
        double s3l = rx - xb, s3h = yb + ry;
        double[] res = { Math.random(), Math.random() };
        switch (weightedChoice(new double[] { s0l * s0h, s1l * s1h, s2l * s2h, s3l * s3h })) {
            case 0:
                res[0] = res[0] * s0l + xt;
                res[1] = res[1] * s0h + yb;
                return res;
            case 1:
                res[0] = res[0] * s1l - rx;
                res[1] = res[1] * s1h + yt;
                return res;
            case 2:
                res[0] = res[0] * s2l - rx;
                res[1] = res[1] * s2h - ry;
                return res;
            case 3:
                res[0] = res[0] * s3l + xb;
                res[1] = res[1] * s3h - ry;
                return res;
        }
        return new double[] { 0.0D, 0.0D };
    }

    private static int weightedChoice(double... options) {
        double sum = 0.0D;
        for (double option : options)
            sum += option;
        double randVal = Math.random();
        randVal *= sum;
        for (int i = 0; i < options.length; i++) {
            if (randVal < options[i])
                return i;
            randVal -= options[i];
        }
        return -1;
    }

    public static double[] getRandXySquare(int radiusMax, int radiusMin, double gShrink, double gCenter) {
        double s0 = radiusMin / radiusMax;
        double s1 = 1.0D - s0;
        double r0 = Math.random();
        double r1 = randomG(gShrink, gCenter);
        r1 = r1 * s0 + Math.sqrt(r1) * s1;
        double[] xy = { s0 + s1 * r1, s0 * r0 + r0 * r1 * s1 };
        if (Math.random() * 2.0D > 1.0D)
            xy[1] = xy[1] * -1.0D;
        xy = TransitionMatrices.multiplyMatrixVector(TransitionMatrices.ROTATIONS_0_90_180_270[(int)(Math.random() * 4.0D)], xy);
        xy[0] = xy[0] * radiusMax;
        xy[1] = xy[1] * radiusMax;
        return xy;
    }

    public static double[] getRandXyCircle(int radiusMax, int radiusMin) {
        return getRandXyCircle(radiusMax, radiusMin, 0.0D, 0.0D);
    }

    public static double[] getRandXyCircle(int radiusMax, int radiusMin, double gShrink, double gCenter) {
        double rMin2 = Math.pow(radiusMin, 2.0D);
        double r = Math.sqrt(((gShrink == 0.0D) ? Math.random() : randomG(gShrink, gCenter)) * (Math.pow(radiusMax, 2.0D) - rMin2) + rMin2);
        double theta = 6.283185307179586D * Math.random();
        double x = (int)(r * Math.cos(theta));
        double y = (int)(r * Math.sin(theta));
        return new double[] { x, y };
    }
}