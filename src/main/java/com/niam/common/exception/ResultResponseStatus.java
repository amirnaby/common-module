package com.niam.common.exception;

import lombok.Getter;

@Getter
public enum ResultResponseStatus {
    SUCCESS(200, 0, "success"),
    FAILURE(500, 1020002, "system.failure"),
    UNKNOWN(500, 1050002, "unknown.error"),
    DB_ERROR(500, 1020002, "db.error"),
    TOKEN_VALIDATION_EXCEPTION(500, 1020002, "token.exception"),
    TOKEN_VERIFY_ERROR(500, 1020002, "token.verify.error"),
    ILLEGAL_ARGUMENT(400, 1020006, "illegal.argument"),
    NULL_REQUIRED_VALUE(400, 1020001, "null.required.value"),
    PHONE_NUMBER_IS_NOT_OK(400, 1010002, "phone.number.is.not.ok"),
    NATIONAL_CODE_IS_NOT_OK(400, 1010003, "national.code.is.not.ok"),
    DATETIME_IS_NOT_OK(400, 1020004, "dateTime.is.not.valid"),
    SMALLER_MODIFY_DATE(400, 2010004, "smaller.modify.date"),
    DUPLICATION_OPERATION(400, 1020008, "duplication.operation"),
    DUPLICATE_TRANSACTION(400, 1020008, "duplicate.transaction"),
    DUPLICATE_ENTITY(400, 1020008, "duplicate.entity"),
    CREATE_DATE_IS_NULL(400, 1020004, "create.date.is.null"),
    MODIFY_DATE_IS_NULL(400, 1020004, "modify.date.is.null"),
    ENTITY_NOT_FOUND(400, 2010002, "entity.not.found"),
    ENTITY_HAS_DEPENDENCIES(400, 2010002, "entity.has.dependencies"),
    //business
    OPERATOR_MACHINE_ALREADY_UNASSIGNED(400, 2010002, "operator.machine.already.unassigned"),
    //user management
    INVALID_SSO_TOKEN(401, 1001, "invalid.sso.token"),
    ACCESS_DENIED(403, 1002, "access.denied"),
    ROLE_CREATION_EXCEPTION(500, 1003, "role.creation.exception"),
    ROLE_UPDATE_EXCEPTION(500, 1004, "role.update.exception"),
    ROLE_DELETE_EXCEPTION(500, 1005, "role.delete.exception"),
    ROLE_NOT_FOUND_EXCEPTION(404, 1006, "role.not.found"),
    ROLE_ASSIGNMENT_EXCEPTION(500, 1007, "role.assignment.exception"),
    USER_NOT_FOUND_EXCEPTION(404, 1008, "user.not.found"),
    USER_DATA_RETRIEVE_EXCEPTION(500, 1009, "user.data.retrieve.exception"),
    USER_DELETE_EXCEPTION(500, 1010, "user.delete.exception"),
    USER_ROLE_REMOVAL_EXCEPTION(500, 1011, "user.role.removal.exception"),
    LOGGING_IN_EXCEPTION(401, 1012, "logging-in.exception"),
    OTP_EXCEPTION(401, 1013, "otp.exception"),
    LOGGING_OUT_EXCEPTION(401, 1014, "logging-out.exception");

    private final String description;
    private final Integer responseCode;
    private final Integer reasonCode;

    ResultResponseStatus(Integer responseCode, Integer reasonCode, String description) {
        this.responseCode = responseCode;
        this.reasonCode = reasonCode;
        this.description = description;
    }
}