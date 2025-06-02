const API_URL = import.meta.env.VITE_API_URL;

export async function getCatalog() {
    const response = await fetch(`${API_URL}/lures/catalog`);
    const json = await response.json();
    return json;
}

export async function getSuggestion(location) {
    try {
        const response = await fetch(`${API_URL}/lures/suggest?location=${location}`);
        if (!response.ok) {
            if (response.status === 400) {
                const errorData = await response.json().catch(() => ({}));
                throw new Error(errorData.message || `${location} is not a valid location`);
            } else if (response.status === 404) {
                throw new Error("Location not found");
            } else if (response.status >= 500) {
                throw new Error("Server error. Please try again later.");
            } else {
                throw new Error(`Request failed with status ${response.status}`);
            }
        }
        const json = await response.json();
        return json;
    } catch (error) {
        console.log(error);
        throw error;
    }
}

