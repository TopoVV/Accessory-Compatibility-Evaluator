document.addEventListener('DOMContentLoaded', e => {
    const form = document.forms['compatibility-form'];
    form.addEventListener('submit', async e => {
        e.preventDefault();
        const url = form.action;
        const compatibilityRequest = {};
        const sources = new FormData(form);
        sources.forEach((value, key) => compatibilityRequest[key] = value);
        const compatibilityResponse = await fetch(url, {
            method: 'post',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(compatibilityRequest)
        });
        console.log(compatibilityResponse);
        const compatibilityResponseBody = await compatibilityResponse.json();
        const results = compatibilityResponseBody.compatibilityResults;
        for (const result of results) {
            const incompatibilities = result.incompatibilities;
            let incCase = '';
            let incDesc = '';
            if(incompatibilities != null) {
                incompatibilities.forEach(inc => {
                    if(incCase.length !== 0) {
                        incCase += ', '
                    }
                    incCase += inc.incompatibilityCase;
                    incDesc += `<p>${inc.incompatibilityDescription}</p>`;
                });
                await Swal.fire({
                    icon: 'error',
                    width: 1000,
                    title: result.compatibilityName,
                    html: incDesc,
                    footer: result.compatibilityStatus + '(' + incCase + ')'
                });
            }
            if(result.compatibilityStatus === 'compatible') {
                await Swal.fire({
                    icon: 'success',
                    width: 1000,
                    title: result.compatibilityName,
                    text: result.compatibilityStatus,
                });
            }
        }
    });
});
