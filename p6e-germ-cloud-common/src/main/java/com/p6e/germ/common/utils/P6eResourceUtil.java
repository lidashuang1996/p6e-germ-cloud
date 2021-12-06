package com.p6e.germ.common.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author lidashuang
 * @version 1.0
 */
public final class P6eResourceUtil {

    public static String readContent(String path) {
        final StringBuilder sb = new StringBuilder();
        final File file = new File(Objects.requireNonNull(
                P6eResourceUtil.class.getClassLoader().getResource(path)).getPath());
        FileReader fr = null;
        BufferedReader br = null;
        try {
            String line;
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String getResourceContent (String path) throws IOException {
        return getResourceContent(path, -1);
    }

    public static String getResourceContent (String path, int index) throws IOException {
        final Resource[] resources = getResources(path);
        if (resources.length == 0) {
            return "";
        } else {
            index = index >= 0 ? index : resources.length + index;
            return readInputStreamToString(resources[index].getInputStream());
        }
    }

    public static Resource[] getResources(String path) throws IOException {
        return new PathMatchingResourcePatternResolver().getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + path);
    }

    public static void main(String[] args) {
        System.out.println(readContent("1.txt"));
    }

    public static String readFile(File file) {
        FileReader fr = null;
        BufferedReader br = null;
        final StringBuilder sb = new StringBuilder();
        try {
            String line;
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String readInputStreamToString(InputStream inputStream) {
        final StringBuilder sb = new StringBuilder();
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            br = new BufferedReader(isr);
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
