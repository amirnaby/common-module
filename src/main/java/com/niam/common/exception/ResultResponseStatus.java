package com.niam.common.exception;

import lombok.Getter;

@Getter
public enum ResultResponseStatus {
    SUCCESS(200, 0, "success"),
    FAILURE(500, 1020002, "system.failure"),
    UNKNOWN(500, 1050002, "unknown.error"),
    DB_ERROR(500, 1020002, "db.error"),

    FPE_TOKEN_VALIDATION_EXCEPTION(500, 1020002, "token.exception"),
    ILLEGAL_ARGUMENT(400, 1020006, "illegal.argument"),

    NULL_REQUIRED_VALUE(400, 1020001, "null.required.value"),
    PHONE_NUMBER_IS_NOT_OK(400, 1010002, "phone.number.is.not.ok"),
    NATIONAL_CODE_IS_NOT_OK(400, 1010003, "national.code.is.not.ok"),
    DATETIME_IS_NOT_OK(400, 1020004, "dateTime.is.not.valid"),
    LAST_VERSION_OF_DOCUMENT(400, 2010001, "last.version.of.document"),
    DOCUMENT_ID_NOT_FOUND(400, 2010002, "document.id.not.found"),
    DOCUMENT_ID_AND_DOCUMENT_CODE_NOT_MATCH(400, 2010003, "document.id.and.document.code.not.match"),
    SMALLER_MODIFY_DATE(400, 2010004, "smaller.modify.date"),
    DOCUMENT_ID_IS_NOT_VALID(400, 2010005, "document.id.is.not.valid"),
    DUPLICATION_OPERATION(400, 1020008, "duplication.operation"),
    //DB
    DOCUMENT_PAYLOAD_NOT_FOUND(400, 1020012, "document.payload.not.found"),
    WRONG_VERSION_DOCUMENT(400, 2010001, "wrong.version.document"),
    DUPLICATE_TRANSACTION(400, 1020008, "duplicate.transaction"),
    //NOT NULL
    CREATE_DATE_IS_NULL(400, 1020004, "create.date.is.null"),
    MODIFY_DATE_IS_NULL(400, 1020004, "modify.date.is.null"),
    DOCUMENT_SIZE_IS_NULL(400, 1020004, "document.size.is.null"),
    SIGNATURE_TYPE_IS_NULL(400, 1020004, "signatureType.is.null"),
    DOCUMENT_TYPE_IS_NULL(400, 1020004, "documentType.is.null"),
    DOCUMENT_NAME_IS_NULL(400, 1020004, "documentName.is.null"),
    OWNER_TYPE_IS_NULL(400, 1020004, "ownerType.is.null"),
    DOCUMENT_OWNER_IS_NULL(400, 1020004, "documentOwner.is.null"),
    DOCUMENT_OWNER_IS_NOT_OK(400, 1020004, "documentOwner.is.not.ok"),
    OWNER_MOBILE_NO_IS_NULL(400, 1020004, "ownerMobileNo.is.null"),
    DOCUMENT_CODE_IS_NULL(400, 1020004, "documentCode.is.null"),
    ORIGINAL_SYSTEM_IS_NULL(400, 1020004, "originalSystem.is.null"),
    STATUS_IS_NULL(400, 1020004, "status.is.null"),
    REQUESTED_AT_IS_NULL(400, 1020004, "requested.at.is.null"),
    ELECTRONIC_FORMAT_IS_NULL(400, 1020004, "electronicFormat.is.null"),
    SCHEMA_VERSION_IS_NULL(400, 1020004, "schemaVersion.is.null"),
    DOCUMENT_VERSION_IS_NULL(400, 1020004, "documentVersion.is.null"),
    TRACK_CODE_IS_NULL(400, 1020004, "trackCode.is.null"),
    METHOD_NAME_IS_NULL(400, 1020004, "methodName.is.null"),
    DOCUMENT_ID_IS_NULL(400, 1020004, "documentId.is.null"),
    DOCUMENT_ID_IS_NOT_OK(400, 1020004, "documentId.is.not.ok"),
    REQUESTED_AT_TO_IS_NULL(400, 1020004, "requestedAtTo.is.null"),
    REQUESTED_AT_FROM_IS_NULL(400, 1020004, "requestedAtFrom.is.null"),
    INQUIRY_OBJECT_LEVEL_IS_NULL(400, 1020004, "inquiryObjectLevel.is.null"),
    EXTERNAL_TRACK_CODE_IS_NULL(400, 1020004, "externalTrackCode.is.null"),
    EVENT_TYPE_IS_NULL(400, 1020004, "eventType.is.null"),
    REQUESTED_TIME_IS_NULL(400, 1020004, "requestedTime.is.null"),
    EVENT_DATA_IS_NULL(400, 1020004, "eventData.is.null"),
    DELIVER_TIME_IS_NULL(400, 1020004, "deliverTime.is.null"),
    DELIVER_CHANNEL_IS_NULL(400, 1020004, "deliverChannel.is.null"),
    REQUIRED_PARAMETERS_ARE_NULL(400, 1020004, "required.parameters.are.null"),
    SIGNED_DOC_IS_NULL(400, 1020004, "signed.doc.is.null"),
    INVALID_PAGE_NUMBER(400, 2010008, "invalid.page.number"),

    DOCUMENT_NOT_FOUND(400, 2010002, "document.not.found"),
    DOCUMENT_PAYLOAD_SCHEMA(500, 1050003, "document.payload.schema.not.found"),
    DOCUMENT_PAYLOAD_TEMPLATE(500, 1050004, "document.payload.template.not.found"),
    DOCUMENT_METADATA_NOT_FOUND(500, 1050005, "document.metadata.not.found"),
    DOCUMENT_APPROVAL_NOT_FOUND(500, 1050006, "document.approval.not.found"),
    DOCUMENT_RELATION_NOT_FOUND(500, 1050007, "document.relation.not.found"),
    DELIVER_DOC_STATUS_NOT_FOUND(500, 1050008, "document.deliverDocStatus.not.found"),
    DOCUMENT_TEMPLATE_NOT_FOUND(500, 1050009, "document.template.not.found"),
    RECEIVE_EVENT_NOT_FOUND(500, 1050010, "receive.event.not.found"),

    DOCUMENT_TYPE_NOT_FOUND(500, 1050010, "document.type.not.found"),
    DOCUMENT_VIEW_LOG_NOT_FOUND(500, 1050011, "document.view.log.not.found"),
    DOCUMENT_RELATION_TYPE_NOT_FOUND(500, 1050012, "document.relation.type.not.found"),
    DOCUMENT_VIEW_SOURCE_TYPE_NOT_FOUND(500, 1050013, "document.view.source.type.not.found"),
    FUNDAMENTAL_MAP_TYPE_NOT_FOUND(500, 1050014, "fundamental.map.not.found"),
    DUPLICATE_FUNDAMENTAL_MAP_FOUND(409, 1050015, "duplicate.fundamental.map.found"),
    FUNDAMENTAL_MAP_TYPE_NOT_FOUND_BY_ID(500, 1050016, "fundamental.map.not.found.by.id"),

    QR_SIGN_ERROR(500, 1020002, "qr.sign.error"),
    IMPORT_SMS_ERROR(500, 1020002, "import.sms.error"),
    IMPORT_SMS_AUTH_ERROR(500, 1020002, "import.sms.auth.error"),
    IMPORT_VERIFY_SIGN_ERROR(500, 1020002, "import.verify.sign.error"),
    IMPORT_VERIFY_SIGN_AUTH_ERROR(500, 1020002, "import.verify.sign.auth.error"),
    IMPORT_VERIFY_SIGN_NOT_VALID(500, 1020002, "import.verify.sign.auth.not.valid"),
    TOKEN_VERIFY_ERROR(500, 1020002, "token.verify.error"),

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
    LOGGING_OUT_EXCEPTION(401, 1014, "logging-out.exception"),

    DOCUMENT_META_DATA_DOCUMENT_ID_IS_NOT_EQUAL_WITH_DOCUMENT_ID(500, 1050016, "document.meta.data.document.id.is.not.equal.with.document.id"),
    DOCUMENT_PAYLOAD_WITH_DOCUMENT_CODE_NOT_FOUND(500, 1050017, "document.payload.with.document.code.not.found"),
    PDF_GENERATION_MODEL_CONVERT(500, 1050018, "pdf-generation.model.convert.has.problem"),
    ELASTIC_CONNECTION_FAILED(500, 1050019, "elastic.connection.failed"),
    ELASTIC_QUERY_FAILED(500, 1050020, "elastic.query.failed"),

    // database syncer
    CANT_SAVE_ORACLE_DATA(400, 1020012, "cant.save.oracle.data"),
    CANT_GET_ORACLE_TRACK_CODE_SET(400, 1020012, "cant.get.oracle.track.code.set"),
    CANT_SAVE_ELASTIC_DATA(400, 1020012, "cant.save.elastic.data"),
    CANT_SAVE_MONGO_DATA(400, 1020012, "cant.save.mongo.data"),
    ELASTIC_TRACK_CODE_NOT_FOUND(400, 1020012, "elastic.track.code.not.found"),
    MONGO_TRACK_CODE_NOT_FOUND(400, 1020012, "mongo.track.code.not.found"),
    ORACLE_TRACK_CODE_NOT_FOUND(400, 1020012, "oracle.track.code.not.found");

    private final String description;
    private final Integer responseCode;
    private final Integer reasonCode;

    ResultResponseStatus(Integer responseCode, Integer reasonCode, String description) {
        this.responseCode = responseCode;
        this.reasonCode = reasonCode;
        this.description = description;
    }
}