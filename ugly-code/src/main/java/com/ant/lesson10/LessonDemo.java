package com.ant.lesson10;

import com.google.common.collect.ImmutableList;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Demo
 * </p>
 *
 * @author Ant
 * @since 2021/1/24 4:06 下午
 */
public class LessonDemo {

    public static void main(String[] args) {

    }

    public void demo1Wrong() {
        /** ----------------------- 错误示例 --------------------- **/
        Object request = null;
        EpubStatus status = null;
        CreateEpubResponse response = createEpub(request);
        if (response.getCode() == 201) {
            status = EpubStatus.CREATED;
        } else {
            status = EpubStatus.TO_CREATE;
        }
    }

    public void demo1Right() {
        Object request = null;
        final CreateEpubResponse response = createEpub(request);
        final EpubStatus status = toEpubStatus(response);

    }

    private EpubStatus toEpubStatus(CreateEpubResponse response) {
        if (response.getCode() == 201) {
            return EpubStatus.CREATED;
        }
        return EpubStatus.TO_CREATE;
    }

    private static CreateEpubResponse createEpub(Object request) {
        return new CreateEpubResponse();
    }



    public void demo2Wrong() throws IOException {
        InputStream is = null;

        try {
            is = new FileInputStream("test");
        } catch (IOException e) {
            // TODO
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public void demo2Right() {
        try (InputStream is = new FileInputStream("test")) {

        } catch (IOException e) {
            // TODO
        }
    }


    public void demo3Wrong() {
        Check check = new Check();

        List<Permission> permissions = new ArrayList<>();
        permissions.add(Permission.BOOK_READ);
        permissions.add(Permission.BOOK_WRITE);
        check.grantTo(Role.AUTHOR, permissions);
    }

    public void demo3Right() {
        Check check = new Check();

        List<Permission> permissions = ImmutableList.of(
                Permission.BOOK_WRITE,
                Permission.BOOK_READ
        );
        check.grantTo(Role.AUTHOR, permissions);
    }

}
