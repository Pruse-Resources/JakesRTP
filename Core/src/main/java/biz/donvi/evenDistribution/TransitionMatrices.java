package biz.donvi.evenDistribution;

public final class TransitionMatrices {
    static final double[][] ROTATION_0 = new double[][]{{1.0D, 0.0D}, {0.0D, 1.0D}};

    static final double[][] ROTATION_90 = new double[][]{{0.0D, -1.0D}, {1.0D, 0.0D}};

    static final double[][] ROTATION_180 = new double[][]{{-1.0D, 0.0D}, {0.0D, -1.0D}};

    static final double[][] ROTATION_270 = new double[][]{{0.0D, 1.0D}, {-1.0D, 0.0D}};

    static final double[][][] ROTATIONS_0_90_180_270 = new double[][][]{ROTATION_0, ROTATION_90, ROTATION_180, ROTATION_270};

    static double[][] getSquish(double amount) {
        return new double[][]{{1.0D,
                Math.cos(amount * Math.PI / 2.0D)}, {0.0D,
                Math.sin(amount * Math.PI / 2.0D)}};
    }

    public static double[] multiplyMatrixVector(double[][] matrix, double[] vector) {
        return new double[]{matrix[0][0] * vector[0] + matrix[0][1] * vector[1], matrix[1][0] * vector[0] + matrix[1][1] * vector[1]};
    }
}