var modalParams = modalParams || {};
var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_CLOSE: function (caller, act, data) {
        if (parent) {
            parent.axboot.modal.close(data);
        }
    },
    PAGE_SEARCH: function (caller, act, data) {
        if (!modalParams.id) return false;
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/booking/' + modalParams.id,
            callback: function (res) {
                caller.formView01.setData(res);
                caller.gridView01.setData(res.memoList || []);
            },
        });
    },
    PAGE_SAVE: function (caller, act, data) {
        if (caller.formView01.validate()) {
            var item = caller.formView01.getData();
            var memos = [].concat(caller.gridView01.getData());
            memos = memos.concat(caller.gridView01.getData('deleted'));
            item.memoList = memos;
            if (!item.id) item.__created__ = true;
            axboot.ajax({
                type: 'POST',
                url: '/api/v1/booking',
                data: JSON.stringify(item),
                callback: function (res) {
                    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                    axToast.push('저장 되었습니다');
                },
            });
        }
    },
    dispatch: function (caller, act, data) {
        var result = ACTIONS.exec(caller, act, data);
        if (result != 'error') {
            return result;
        } else {
            return false;
        }
    },
    ITEM_CLICK: function (caller, act, data) {
        caller.formView01.setData(data);
    },
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow('selected');
    },
    FORM_CLEAR: function (caller, act, data) {
        axDialog.confirm({ msg: LANG('ax.script.form.clearconfirm') }, function () {
            if (this.key == 'ok') {
                caller.formView01.clear();
                caller.gridView01.clear();
                $('[data-ax-path="arrDt"]').focus();
            }
        });
    },
    GUEST_MODAL:function(caller, act, data){
        
    }
});

var CODE = {};

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    var _this = this;
    _this.pageButtonView.initView();
    _this.gridView01.initView();
    _this.formView01.initView();
    _this.guestView01.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            save: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
            close: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_CLOSE);
            },
            guestModal: function(){
                ACTIONS.dispatch(ACTIONS.PAGE_CLOSE);
            }
        });
    },
});
fnObj.guestView01 =axboot.viewExtend(axboot.formView, {
    open:function(data){
        if (!data) data = {};
        axboot.modal.open({
            width: 730,
            height: 600,
            iframe: {
                param: 'id=' + (data.id || ''),
                url: 'guest-modal.jsp',
            },
            header: { title: '투숙객 조회' },
            callback: function (data) {
                if (data) {
                    caller.formView01.setGuest(data);
                }
                this.close();
            },
        });
    },
    close: function(data){
        if (parent) {
            parent.axboot.modal.close(data);
        }
    },

},

fnObj.formView01 = axboot.viewExtend(axboot.formView, {
    setGuest: function (data) {
        this.model.set('guestId', data.id || '');
        this.model.set('guestNm', data.guestNm || '');
        this.model.set('guestNmEng', data.guestNmEng || '');
        this.model.set('guestTel', data.guestTel || '');
        this.model.set('email', data.email || '');
        this.model.set('gender', data.gender || '');
        this.model.set('brth', data.brth || '');
        this.model.set('langCd', data.langCd || '');
    },
    getDefaultData: function () {
        return { adultCnt: '1', chldCnt: '0' };
    },

    getData: function () {
        var data = this.modelFormatter.getClearData(this.model.get());
        return $.extend({}, data);
    },

    setData: function (data) {
        var _this = this;
        data = $.extend({}, data);
        $('.js-rsvNum').text('예약번호:' + data.rsvNum);
        // _this.model.set('.js-rsvNum', data.rsvNum);
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
        }); //달력

        axboot.buttonClick(this, 'data-searchview-btn', {
            modal: function () {
                ACTIONS.dispatch(ACTIONS.GUEST_MODAL);
            },
        }); //검색 모달
    },
    dateCnt: function () {
        var _this = this;
        this.arrDt.on('change', function () {
            var arrDt = $(this).val();
            var depDt = _this.depDt.val();
            if (!arrDt || !depDt) return;

            var momArrDt = moment(arrDt);
            var momDepDt = moment(depDt);
            var nightCnt = momDepDt.diff(momArrDt, 'days');
            if (nightCnt < 1) {
                nightCnt = 1;
                _this.model.set('depDt', momArrDt.add(nightCnt, 'days').format('yyyy-MM-DD'));
            }
            _this.model.set('nightCnt', nightCnt);
        });
        this.depDt.on('change', function () {
            var arrDt = _this.arrDt.val();
            var depDt = $(this).val();
            if (!arrDt || !depDt) return;

            var momArrDt = moment(arrDt);
            var momDepDt = moment(depDt);
            var nightCnt = momDepDt.diff(momArrDt, 'days');
            if (nightCnt < 1) {
                nightCnt = 1;
                _this.model.set('arrDt', momDepDt.add(-nightCnt, 'days').format('yyyy-MM-DD'));
            }
            _this.model.set('nightCnt', nightCnt);
        });
        this.nightCnt.on('change', function () {
            var arrDt = _this.arrDt.val();
            if (!arrDt) return;

            var nightCnt = _this.nightCnt.val();
            if (nightCnt < 1) {
                nightCnt = 1;
                _this.model.set('nightCnt', nightCnt);
            }
            _this.model.set('depDt', moment(arrDt).add(nightCnt, 'days').format('yyyy-MM-DD'));
        });
    },
    initView: function () {
        var _this = this; // fnObj.formView01

        _this.target = $('.js-form');

        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작
        this.initEvent();

        this.arrDt = $('.js-arrDt');
        this.depDt = $('.js-depDt');
        this.nightCnt = $('.js-nightCnt');
        this.dateCnt();
    },
});

/**
 * gridView
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                {
                    key: 'memoDtti',
                    label: '작성일',
                    width: 150,
                    align: 'center',
                    editor: 'text',
                    formatter: function () {
                        return moment().format('YYYY-MM-DD');
                    },
                },
                { key: 'memoCn', label: '메모', width: 900, align: 'center', editor: 'text' },
            ],
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
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == 'modified' || _type == 'deleted') {
            list = ax5.util.filter(_list, function () {
                return this.id;
            });
        } else {
            list = _list;
        }
        return list;
    },
    addRow: function () {
        //var today = moment.format('yyyy-MM-DD hh:mm');
        this.target.addRow({ __created__: true }, 'last');
    },
});
