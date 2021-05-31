var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        var paramObj = $.extend(caller.searchView.getData(), data);
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/booking',
            data: paramObj,
            callback: function (res) {
                caller.gridView01.setData(res);
            },
            options: {
                // axboot.ajax 함수에 2번째 인자는 필수가 아닙니다. ajax의 옵션을 전달하고자 할때 사용합니다.
                onError: function (err) {
                    console.log(err);
                },
            },
        });

        return false;
    },
    PAGE_SAVE: function (caller, act, data) {
        var saveList = [].concat(caller.gridView01.getData('modified'));

        axboot.ajax({
            type: 'POST',
            url: '/api/v1/booking',
            data: JSON.stringify(saveList),
            callback: function (res) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                axToast.push('저장 되었습니다');
            },
        });
    },
    STATE_MODAL: function (caller, act, data) {
        if (!data) data = {};
        data.modalTyp = 'ChkIn';
        axboot.modal.open({
            width: 1200,
            height: 900,
            iframe: {
                param: 'id=' + data.id + '&modalTyp=' + data.modalTyp,
                url: '/jsp/pms/book/state-modal.jsp',
            },
            header: { title: '체크인' },
            callback: function (data) {
                if (data && data.dirty) {
                    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                }
                this.close();
            },
        });
    },
    ITEM_CLICK: function (caller, act, data) {},
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow('selected');
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
    EXCEL_DOWN: function (caller, act, data) {
        var frm = $('.js-grid').get(0);
        frm.action = '/api/v1/booking/exceldown';
        frm.enctype = 'application/x-www-form-urlencoded';
        frm.submit();
    },
    RESET_SEARCH: function (caller, act, data) {
        caller.searchView.getdefaultsearch();
    },
});

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.searchView.initView();
    this.gridView01.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            search: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            'form-clear': function () {
                ACTIONS.dispatch(ACTIONS.RESET_SEARCH);
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
        this.filter = $('.js-filter');
        this.rsvNum = $('.js-rsvNum');
        this.arrDt = $('.js-arrDt');

        $('[data-ax5picker="arrDt"]').ax5picker({
            direction: 'auto',
            content: {
                type: 'date',
            },
        });
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            filter: this.filter.val(),
            rsvNum: this.rsvNum.val(),
            arrDt: this.arrDt.val(),
        };
    },
    getdefaultsearch: function () {
        $('input:text').val('');
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
                { key: 'rsvNum', label: '예약번호', width: 160, align: 'center' },
                { key: 'rsvDt', label: '예약일', width: 160, align: 'center' },
                { key: 'guestNm', label: '투숙객', width: 100, align: 'center' },
                {
                    key: 'roomTypCd',
                    label: '객실타입',
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['ROOM_TYPE'].map[this.value];
                    },
                },
                { key: 'roomNum', label: '객실번호', width: 100, align: 'center' },
                { key: 'arrDt', label: '도착일', width: 100, align: 'center' },
                { key: 'depDt', label: '출발일', width: 100, align: 'center' },
                {
                    key: 'srcCd',
                    label: '예약경로',
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['RESERVATION_ROUTE'].map[this.value];
                    },
                },
                {
                    key: 'saleTypCd',
                    label: '판매유형',
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['SALE_TYPE'].map[this.value];
                    },
                },
                {
                    key: 'sttusCd',
                    label: '상태',
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['STAY_STATUS'].map[this.value];
                    },
                },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                },
                onDBLClick: function () {
                    ACTIONS.dispatch(ACTIONS.STATE_MODAL, this.item);
                },
            },
        });

        axboot.buttonClick(this, 'data-grid-view-01-btn', {
            sstus: function () {
                ACTIONS.dispatch(ACTIONS.STATE_CHANGE);
            },
        });
        this.sttusCd = $('.js-sttusCd');
    },

    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == 'selected') {
            list = ax5.util.filter(_list, function () {
                return this.id;
            });
        } else {
            list = _list;
        }
        return list;
    },
});
