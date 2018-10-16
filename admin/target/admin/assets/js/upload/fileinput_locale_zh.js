/*!
 * FileInput Spanish (Latin American) Translations
 *
 * This file must be loaded after 'fileinput.js'. Patterns in braces '{}', or
 * any HTML markup tags in the messages must not be converted or translated.
 *
 * @see http://github.com/kartik-v/bootstrap-fileinput
 *
 * NOTE: this file must be saved in UTF-8 encoding.
 */
(function ($) {
    "use strict";
    $.fn.fileinput.locales.es = {
        fileSingle: '单个文件',
        filePlural: '多个文件',
        browseLabel: '选择文件 ',
        removeLabel: '删除文件',
        removeTitle: '删除选中文件',
        cancelLabel: '取消',
        cancelTitle: '取消上传',
        uploadLabel: '上传',
        uploadTitle: '上传选中文件',
        msgSizeTooLarge: '文件大小超过最大限制（ <b>{maxSize} KB</b>）！请重新操作！',
        msgFilesTooLess: '文件数量必须大于 <b>{n}</b> {files} ，请重新上传！',
        msgFilesTooMany: '目前只支持单张图片上传！ 请重新操作！',
        msgFileNotFound: '文件 "{name}" 未找到!',
        msgFileSecured: 'Security restrictions prevent reading the file "{name}".',
        msgFileNotReadable: 'File "{name}" is not readable.',
        msgFilePreviewAborted: 'File preview aborted for "{name}".',
        msgFilePreviewError: 'An error occurred while reading the file "{name}".',
        msgInvalidFileType: 'Invalid type for file "{name}". Only "{types}" files are supported.',
        msgInvalidFileExtension: 'Invalid extension for file "{name}". Only "{extensions}" files are supported.',
        msgValidationError: 'File Upload Error',
        msgLoading: 'Loading file {index} of {files} &hellip;',
        msgProgress: 'Loading file {index} of {files} - {name} - {percent}% completed.',
        msgSelected: '选中{n}个文件',
        msgFoldersNotAllowed: 'Drag & drop files only! {n} folder(s) dropped were skipped.',
        dropZoneTitle: '拖拽到这个区域'
    };

    $.extend($.fn.fileinput.defaults, $.fn.fileinput.locales.es);
})(window.jQuery);
