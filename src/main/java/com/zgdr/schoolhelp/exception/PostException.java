package com.zgdr.schoolhelp.exception;

import com.zgdr.schoolhelp.domain.Post;
import com.zgdr.schoolhelp.enums.*;
/**
 * @创建者 fishkk
 * @创建时间 描述
 */
public class PostException extends ExceptionParent {
    public PostException(PostResultEnum postResultEnum) {
        super(postResultEnum);
    }
}
