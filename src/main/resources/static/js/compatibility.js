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
            let incCase = "";
            let incDesc = "";
            incompatibilities.forEach(inc => {
                incCase += inc.incompatibilityCase + '\n';
                incDesc += inc.incompatibilityDescription + '\n';
            });
            if(result.compatibilityStatus === 'incompatible') {
                await Swal.fire({
                    icon: 'error',
                    width: 700,
                    title: result.compatibilityName,
                    text: result.compatibilityStatus + '(' + incCase + ')',
                    footer: incDesc
                });
            }
            if(result.compatibilityStatus === 'compatible') {
                await Swal.fire({
                    icon: 'success',
                    width: 700,
                    title: result.compatibilityName,
                    text: result.compatibilityStatus,
                });
            }
        }
    });
});
