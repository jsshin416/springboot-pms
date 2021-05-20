var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: 'GET',
            url: "/api/v1/state",
            data: caller.searchView.getData(),
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
        var saveList = [].concat(caller.gridView01.getData(''));
        saveList = saveList.concat(caller.gridView01.getData('deleted'));

        axboot.ajax({
            type: 'POST',
            url: "/api/v1/state",
            data: JSON.stringify(saveList),
            callback: function (res) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                axToast.push('저장 되었습니다');
            },
        });
    },
    ITEM_CLICK: function (caller, act, data) {
        caller.formView01.setData(data);
    },
    MODAL_OPEN: function (caller, act, data) {
        if (!data) data = {};
        axboot.modal.open({
            width: 730,
            height: 600,
            iframe: {
                param: 'id=' + (data.id || ''),
                url: 'state_modal.jsp',
            },
            header: { title: '투숙객 조회' },
            callback: function (data) {
                if (data && data.dirty) {
                    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                }
                this.close();
            },
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
    EXCEL_DOWN: function (caller, act, data) {
        var frm = $('.js-form').get(0);
        frm.action = '/api/v1/guest/exceldown';
        frm.enctype = 'application/x-www-form-urlencoded';
        frm.submit();
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
            save: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
            excel: function () {},
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
        this.target.attr('onsubmit', 'return false');
        this.filter = $('#filter');
        this.target.on('keydown.search', 'input, .form-control', function (e) {
            if (e.keyCode === 13) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            }
        });
        this.rsvNum=$('.js-rsvNum');
        this.rsvDt =$('.js-rsvDt');
        this.roomTypCd = $('.js-roomTypCd');
        this.arrDt =$('.js-arrDt');
        this.depDt =$('.js-depDt');
        this.target.find('[data-ax5picker="date"]').ax5picker({
            direction: "auto",
            content: {
                type: 'date'
            }
        });
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            filter: this.filter.val(),
            rsvNum: this.rsvNum.val(),
            rsvDt: this.rsvDt.val(),
            roomTypCd: this.roomTypCd.val(),
            arrDt: this.arrDt.val(),
            depDt: this.depDt.val(),
           
        };
    },
});

/**
 * gridView
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            showRowSelector: true,
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                { key: 'rsvNum', label: '예약번호', width: 160, align: 'center', editor: 'readonly' },
                { key: 'rsvDt', label: '예약일', width: 160, align: 'center', editor: 'readonly' },
                { key: 'guestNm', label: '투숙객', width: 100, align: 'center', editor: 'readonly' },
                { key: 'roomTypCd', label: '객실타입', width: 100, align: 'center', editor: 'readonly' },
                { key: 'roomNum', label: '객실번호', width: 100, align: 'center', editor: 'readonly' },
                { key: 'arrDt', label: '도착일', width: 100, align: 'center', editor: 'readonly' },
                { key: 'depDt', label: '출발일', width: 100, align: 'center', editor: 'readonly' },
                { key: 'srcCd', label: '예약경로', width: 100, align: 'center', editor: 'readonly' },
                { key: 'saleTypCd', label: '판매유형', width: 100, align: 'center', editor: 'readonly' },
                { key: 'sttusCd', label: '상태', width: 100, align: 'center', editor: 'readonly' },

            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                },
            },
        });

        axboot.buttonClick(this, 'data-grid-view-01-btn', {
            modal: function () {
                ACTIONS.dispatch(ACTIONS.MODAL_OPEN);
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
   
});

