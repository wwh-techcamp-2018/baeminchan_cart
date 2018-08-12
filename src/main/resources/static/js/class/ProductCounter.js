class ProductCounter {

    constructor() {
        this.handleCounterEvents = this.handleCounterEvents.bind(this);
        this.validateCounter = this.validateCounter.bind(this);
    }

    delegateFrom(element) {
        element.addEventListener('click', this.handleCounterEvents);
        element.addEventListener('keyup', this.handleCounterEvents);
        element.addEventListener('change', this.validateCounter);
        element.addEventListener('input', this.validateCounter);
    }

    handleCounterEvents(e) {
        if (e.target.classList.contains('up') || e.key === "ArrowUp") {
            e.preventDefault();
            return this.updateCounter(e.target, 1);
        }
        if (e.target.classList.contains('down') || e.key === "ArrowDown") {
            e.preventDefault();
            return this.updateCounter(e.target, -1);
        }
    }

    updateCounter(target, count) {
        const counterInput = target.closest('.prd_account').querySelector('.buy_cnt');
        counterInput.value = parseInt(counterInput.value) + count;
        this.validateCounter({ target: counterInput });
    }

    validateCounter({ target }) {
        if (!target.classList.contains('buy_cnt')) {
            return;
        }

        if (target.value === '' || isNaN(target.value) || parseInt(target.value) < 1) {
            target.value = 1;
        }
    }

}