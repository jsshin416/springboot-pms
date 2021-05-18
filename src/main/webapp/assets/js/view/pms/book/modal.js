var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_CLOSE: function (caller, act, data) {
        if (parent) {
            parent.axboot.modal.close();
        }
    },
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/guest',
            data: caller.searchView.getData(),
            callback: function (res) {
                caller.gridView01.setData(res);
            },
        });
        return false;
    },
    ITEM_CLICK: function (caller, act, data) {
        ACTIONS.dispatch(ACTIONS.PAGE_CHOICE);
    },
});

var CODE = {};

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    // var _this = this;
    this.pageButtonView.initView();
    this.gridView01.initView();
    this.formView01.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            search: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            choice: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_CHOICE);
            },
            fn1: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_DEL);
            },
            close: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_CLOSE);
            },
        });
    },
});

/**
 * gridView
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            showLineNumber: false,
            showRowSelector: true,
            frozenColumnIndex: 0,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                { key: 'guestNm', label: '이름', width: 100, align: 'left' },
                { key: 'guestTel', label: '연락처', width: 100, align: 'left' },
                { key: 'email', label: '이메일', width: 150, align: 'center' },
                { key: 'gender', label: '성별', width: 70, align: 'center' },
                { key: 'brth', label: '생년월일', width: 150, align: 'center' },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex);
                    ACTIONS.dispatch(ACTIONS.ITEM_CLICK, this.item);
                },
            },
        });

        axboot.buttonClick(this, 'data-grid-view-01-btn', {
            add: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_ADD);
            },
            delete: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_DEL);
            },
        });
    },
});

fnObj.formView01 = axboot.viewExtend(axboot.formView, {
    getDefaultData: function () {
        return {};
    },

    getData: function () {
        var data = this.modelFormatter.getClearData(this.model.get());
        return $.extend({}, data);
    },

    setData: function (data) {
        if (typeof data === 'undefined') data = this.getDefaultData();
        data = $.extend({}, data);
        this.model.setModel(data);
        this.modelFormatter.formatting();
    },
    validate: function () {
        var item = this.model.get();

        var rs = this.model.validate();
        if (rs.error) {
            axDialog.alert(LANG('ax.script.form.validate', rs.error[0].jquery.attr('title')), function () {
                rs.error[0].jquery.focus();
            });
            return false;
        }

        // required 이외 벨리데이션 정의
        var pattern;
        if (item.guestTel && !(pattern = /^([0-9]{3})\-?([0-9]{4})\-?([0-9]{4})$/).test(item.guestTel)) {
            axDialog.alert('연락처 형식을 확인하세요.'),
                function () {
                    $('[data-ax-path="guestTel"]').focus();
                };
            return false;
        }
        var pattern;
        if (item.email) {
            pattern = /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.(?:[A-Za-z0-9]{2,}?)$/i;
            if (!pattern.test(item.email)) {
                axDialog.alert('이메일 형식을 확인하세요.', function () {
                    $('[data-ax-path="email"]').focus();
                });
                return false;
            }
        }

        return true;
    },
    initEvent: function () {
        this.target.find('[data-ax5picker="date"]').ax5picker({
            direction: 'auto',
            content: {
                type: 'date',
            },
        });
    },

    initView: function () {
        var _this = this; // fnObj.formView01

        _this.target = $('.js-form');

        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작
        this.initEvent();
    },
});
