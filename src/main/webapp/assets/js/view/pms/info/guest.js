var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        var paramObj = $.extend(caller.searchView.getData(), data);
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/guest',
            data: paramObj,
            callback: function (res) {
                caller.formView01.clear();
                caller.gridView01.setData(res);
                caller.gridView02.clear();
            },
            options: {
                // axboot.ajax 함수에 2번째 인자는 필수가 아닙니다. ajax의 옵션을 전달하고자 할때 사용합니다.
                onError: function (err) {
                    console.log(err);
                },
            },
        });
    },
    PAGE_SAVE: function (caller, act, data) {
        if (caller.formView01.validate()) {
            var item = caller.formView01.getData();
            if (!item.id) item.__created__ = true;
            axboot.ajax({
                type: 'POST',
                url: '/api/v1/guest',
                data: JSON.stringify(item),
                callback: function (res) {
                    axToast.push('저장 되었습니다');
                    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                },
            });
        }
    },
    ITEM_CLICK: function (caller, act, data) {
        var id = data.id;
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/guest/' + id,
            callback: function (res) {
                caller.formView01.setData(res);
                caller.gridView02.setData(res.bookingList || []);
            },
        });
    },
    EXCEL_DOWN: function (caller, act, data) {
        var frm = $('.js-grid').get(0);
        frm.action = '/api/v1/guest/exceldown';
        frm.enctype = 'application/x-www-form-urlencoded';
        frm.submit();
    },
    FORM_CLEAR: function (caller, act, data) {
        axDialog.confirm({ msg: LANG('ax.script.form.clearconfirm') }, function () {
            if (this.key == 'ok') {
                caller.formView01.clear();
                $('[data-ax-path="guestNm"]').focus();
            }
        });
    },
    dispatch: function (caller, act, data) {
        var result = ACTIONS.exec(caller, act, data);
        if (result != 'error') {
            return result;
        } else {
            // 직접코딩
            return false;
        }
    },
});

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.searchView.initView();
    this.gridView01.initView();
    this.formView01.initView();
    this.gridView02.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            search: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            save: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
            excel: function () {
                ACTIONS.dispatch(ACTIONS.EXCEL_DOWN);
            },
        });
    },
});

//== view 시작
/**
 * searchView
 */
fnObj.searchView = axboot.viewExtend(axboot.searchView, {
    initView: function () {
        this.target = $(document['searchView0']);
        this.target.attr('onsubmit', 'return false;');
        this.target.on('keydown.search', 'input, .form-control', function (e) {
            if (e.keyCode === 13) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            }
        });
        this.guestNm = $('.js-guestNm');
        this.guestTel = $('.js-guestTel');
        this.email = $('.js-email');
        this.filter = $('#filter');
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            filter: this.filter.val(),
            guestNm: this.guestNm.val(),
            guestTel: this.guestTel.val(),
            email: this.email.val(),
        };
    },
});

/**
 * gridView
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        this.target = axboot.gridBuilder({
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                { key: 'guestNm', label: '이름', width: 100, align: 'center', editor: 'text' },
                { key: 'guestTel', label: '연락처', width: 150, align: 'center', editor: 'text' },
                { key: 'email', label: '이메일', width: 150, align: 'center', editor: 'text' },
                { key: 'gender', label: '성별', width: 70, align: 'center', editor: 'text' },
                { key: 'brth', label: '생년월일', width: 150, align: 'center', editor: 'text' },
                { key: 'langCd', label: '언어', width: 150, align: 'center', editor: 'text' },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                    ACTIONS.dispatch(ACTIONS.ITEM_CLICK, this.item);
                },
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
        axboot.buttonClick(this, 'data-form-view-01-btn', {
            'form-clear': function () {
                ACTIONS.dispatch(ACTIONS.FORM_CLEAR);
            }, //1번만 실행 하거나 init, eventbinding 나중에 호출 할 수도 있어서 따로 분리
        });

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

fnObj.gridView02 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-02"]'),
            columns: [
                {
                    key: 'rsvDt',
                    label: '투숙일',
                    width: 150,
                    align: 'center',
                    editor: 'text',
                    formatter: function () {
                        return moment(this.item.arrDt).format('YY.M.D') + '-' + moment(this.item.depDt).format('YY.M.D');
                    },
                },
                { key: 'nightCnt', label: '숙박수', width: 100, align: 'center', editor: 'text' },
                { key: 'roomNum', label: '객실번호', width: 100, align: 'center', editor: 'text' },
                { key: 'roomTypCd', label: '객실타입', width: 100, align: 'center', editor: 'text' },
                { key: 'rsvNum', label: '투숙번호', width: 150, align: 'center', editor: 'text' },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                },
            },
        });
    },
});
